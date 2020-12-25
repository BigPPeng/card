package com.test.io;

import com.alibaba.fastjson.JSON;
import com.card.test.excel.WriteExcel;
import com.card.test.thrift.GsonUtil;
import com.elong.hotel.searchagent.thrift.bean.CustomerAttribute;
import com.elong.hotel.searchagent.thrift.bean.InnerSearchRequest;
import com.elong.hotel.searchagent.thrift.bean.InnerSearchType;
import com.elong.hotel.searchagent.thrift.bean.ProductAttribute;
import com.elong.hotel.searchagent.thrift.dss.DSSSearchCanBookRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import edu.emory.mathcs.backport.java.util.Arrays;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by cuihp on 2020/12/17.
 */
public class NioRead {
    private static final String BASE_FILE = "/Users/cuihp/Desktop/app/ds/1";
    private static final String BASE_FILE_2 = "/Users/cuihp/Desktop/app/ds/log";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 读取文件
        List<String> allRequest = getRequest();
        System.out.println("读取文件成功");
        // 过滤&序列化
        String[][] filterReasons = new String[][]{{"SearchInner2","kDetailSearch"}};
        List<InnerSearchRequest> all = Lists.newArrayList();
        List<Request> error = Lists.newArrayList();
        allRequest.stream().filter(filterRequest(filterReasons)).forEach(parseObject(all, error));
        writeRequest(error, "error_first");
        System.out.println("文件请求总数:"+allRequest.size()+" 序列化后数目:"+all.size()+" 错误:"+error.size());

        // 聚类

        Map<Integer, List<InnerSearchRequest>> collect = all.stream().collect(Collectors.groupingBy((v) -> v.caller_attr.app_from));


        List<InnerSearchRequest> innerSearchRequests = collect.get(4);


        //List<InnerSearchRequest> collect1 = innerSearchRequests.stream().filter((c) -> "MobileApi".equals(c.caller_attr.channel)).collect(Collectors.toList());



        Map<String, List<InnerSearchRequest>> stringListMap = mapByUseType(innerSearchRequests);


        List<InnerSearchRequest> only_old = stringListMap.get("only_old");
        if (only_old != null) {
            Map<String,Integer> map = Maps.newHashMap();
            only_old.forEach((c) -> {
                System.out.println(c.caller_attr.ip);
                Integer integer = map.get(c.caller_attr.ip);
                if (integer == null){
                    map.put(c.caller_attr.ip,1);
                } else {
                    map.put(c.caller_attr.ip,++integer);
                }
            });
            System.out.println(JSON.toJSONString(map));
            System.out.println(only_old.size());
            writeRequest(only_old,"App_4");
        } else {
            System.out.println("Empty");
        }




//        ArrayList<List<String>> write = Lists.newArrayList();
//        collect.forEach((k, v) -> {
//            Map<String, List<InnerSearchRequest>> collect1 = mapByUseType(v);
//            collect1.forEach((k2, v2) -> {
//                int size = v2.size();
//                double v1 = ((double) size / (double) v.size()) * 100;
//                System.out.println("app_from：" + k + "\t\t" + k2 + " 数目：\t\t" + size + " \t\t占比：" + v1);
//                List<String> cell = Lists.newArrayList(k+"",k2+"", size+"",v1+"");
//                write.add(cell);
//            });
//
//        });
//        ArrayList<String> head = Lists.newArrayList("AppForm", "使用类型", "数目", "占比");
//        WriteExcel.writeExcel(head,write,BASE_FILE_2+"/AppForm占比.xlsx");
    }

    private static Predicate<String> filterRequest(String[][] filterReasons) {
        return (s) -> {
            if (s == null || s.length() == 0)
                return false;
            // 取并集
            for (String[] filterReason : filterReasons) {
                // 取交集
                boolean allOk = true;
                for (String s1 : filterReason) {
                    allOk &= s.contains(s1);
                }
                if (allOk) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Consumer<String> parseObject(List<InnerSearchRequest> all, List<Request> error) {
        return (valueInfo) -> {
            String[] split = valueInfo.split("request:");
            try {
                Request searchRequest = GsonUtil.parseObj(split[1], Request.class);
                if (searchRequest.getFirst_request() == null) {
                    error.add(searchRequest);
                } else {
                    all.add(searchRequest.getFirst_request());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(GsonUtil.toJson(valueInfo));
            }
        };
    }

    private static Map<String, List<InnerSearchRequest>> mapByUseType(List<InnerSearchRequest> v) {
        return v.stream().collect(Collectors.groupingBy((s) -> {
            if (s == null || s.product_attr == null) {
                return "Request_is_null_or_product_attr_is_null";
            }
            if (!s.product_attr.isSetUse_day_promotion() && !s.product_attr.isSetUse_group_day_promotion()) {
                return "both_not_set";
            }
            if (s.product_attr.use_day_promotion && !s.product_attr.use_group_day_promotion) {
                return "only_old";
            }
            if (s.product_attr.use_day_promotion) {
                return "both_use";
            }
            return "use_new";
        }));
    }


    private static void testAll() {


        String[] requestType = new String[]{"searchCanBookHotels", "SearchCanBook", "SearchInner2",
                "searchListHotels", "searchHotelSingleProduct", "searchHotel", "ChangePrice4Order"};
        long l1 = System.currentTimeMillis();
        List<String> allRequest = getRequest();

        long l2 = System.currentTimeMillis();
        System.out.println("读文件耗时：" + (l2 - l1) + "MS");
        // 过滤 & 分类
        String[] saveType = {"SearchCanBook", "SearchInner2", "searchListHotels"};
        Map<String, List<String>> typeRequest = filterAndClassification(allRequest, saveType);
        printCountInfo(typeRequest);// 打印数据信息

        long l3 = System.currentTimeMillis();
        System.out.println("过滤分类耗时：" + (l3 - l2) + "MS");

        Map<String, List<InnerSearchRequest>> innerRequest = Maps.newHashMap();
        List<Request> error = Lists.newArrayList();
        typeRequest.forEach((k, v) -> {
            v.forEach((valueInfo) -> {
                String[] split = valueInfo.split("request:");
                try {
                    Request searchRequest = GsonUtil.parseObj(split[1], Request.class);
                    if (searchRequest.getFirst_request() == null) {
                        error.add(searchRequest);
                    } else {
                        innerRequest.merge(k, Lists.newArrayList(searchRequest.getFirst_request()), (b, c) -> {
                            b.addAll(c);
                            return b;
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        });
        writeRequest(error, "error_first");

//        countDownLatch.wait();
        long l4 = System.currentTimeMillis();
        System.out.println("序列化耗时：" + (l4 - l3) + "MS");
        /*
            enum InnerSearchType
             {
             kRegionSearch = 1,  //本地检索  (带cityid的，可同事支持文本模糊检索)
             kNearBySearch = 2,  //周边检索 （不需要带region,目前不同时支持文本模糊检索）
             kBoundSearch = 3,   //矩形检索（不需要带城市编号，目前只支持正南正北矩形，目前不同时支持模糊检索）
             kDetailSearch = 4,  //详情检索 (可一次传入多个酒店,可选择是否返回静态信息,也即静态摘要)
             kStaticDigest = 5,  //静态摘要获取 (可一次传入多个酒店)
             }
         */


        // use_day_promotion
        // use_group_day_promotion

//        List<InnerSearchRequest> special = Lists.newArrayList();
//        innerRequest.forEach((k, v) -> {
//            System.out.println("接口类型：" + k + "\t数目：" + v.size());
//            Map<Boolean, List<InnerSearchRequest>> collect2 = v.stream().collect(Collectors.groupingBy((s) -> {
//                try {
//                    if (s == null || s.product_attr == null) {
//                        special.add(s);
//                        return false;
//                    }
//                    return s.product_attr.use_day_promotion && !s.product_attr.use_group_day_promotion;
//                } catch (Exception e) {
//                    special.add(s);
//                    e.printStackTrace();
//                }
//                return false;
//            }));
//            collect2.forEach((k2, v2) -> {
//                String key2 = k2 ? " 旧相容组" : " 新相容组";
//                System.out.println("接口类型：" + k + "\t" + key2 + "数目：" + v2.size());
//                if (k2) {
//                    writeExcel(v2, k);
//                }
//            });
//            System.out.println();
//        });
//        long l5 = System.currentTimeMillis();
//        System.out.println("解析耗时：" + (l5 - l4) + "MS");

        sortThreeLevel(innerRequest);
    }

    private static void clearLogDi() {
        File file = new File(BASE_FILE_2);
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File file1 : files) {
                file1.deleteOnExit();
            }
        }
    }

//    public static void main(String[] args) {
//        File file = new File(BASE_FILE_2);
//        File[] files = file.listFiles();
//        if (files != null && files.length > 0) {
//            for (File file1 : files) {
//                System.out.println(file1.delete());
//            }
//        }
//
//    }

    private static void writeExcel(List<InnerSearchRequest> data, String file) {
        List<InnerSearchRequest> error = Lists.newArrayList();
        ArrayList<String> head = Lists.newArrayList("AppForm", "SearchFromEnd", "Order_id", "use_day_promotion", "use_group_day_promotion");
        ArrayList<List<String>> write = Lists.newArrayList();
        data.forEach((v) -> {
            List<String> a = Lists.newArrayList();
            if (v != null) {
                try {
                    a.add(v.caller_attr.app_from + "");
                    a.add(v.caller_attr.searchFromEnd + "");
                    a.add(v.customer_attr.order_id + "");
                    a.add(v.product_attr.use_day_promotion + "");
                    a.add(v.product_attr.use_group_day_promotion + "");
                } catch (Exception e) {
                    error.add(v);
                }
            }
            write.add(a);
        });
        WriteExcel.writeExcel(head, write, BASE_FILE_2 + "/" + "import_" + file + "_" + System.currentTimeMillis() + ".xlsx");
        writeRequest(error, file);
    }


//    public static void main(String[] args) {
//
//        InnerSearchRequest innerSearchRequest = new InnerSearchRequest();
//        innerSearchRequest.setCustomer_attr(new CustomerAttribute());
//        innerSearchRequest.setProduct_attr(new ProductAttribute().setAdvance_coupon_product_code("ddd").setBooking_room_num(100));
//        writeRequest(Lists.newArrayList(innerSearchRequest),"testWrite");
//    }

    private static List<String> getRequest() {
        List<File> allFile = getAllFile();
        List<String> allRequest = Lists.newArrayList();
        allFile.parallelStream().forEach((v) -> {
            try {
                allRequest.addAll(getRequestOfFile(v));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        File file = new File("/Users/cuihp/Desktop/gs_request.log");
//        List<String> request = getRequestOfFile(file);
        return allRequest;
    }


    private static <T> boolean writeRequest(List<T> param, String fileNameTemp) {
        if (param == null || param.isEmpty()) {
            return true;
        }
        System.out.println("错误数据大小：" + param.size());
        long currentTimeMillis = System.currentTimeMillis();
        String fileName = BASE_FILE_2 + "/" + fileNameTemp + "_" + currentTimeMillis / 1000 + ".txt";
        File file = new File(fileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            boolean newFile = file.createNewFile();
            //System.out.println((newFile ? "成功" : "失败" )+ "文件名：" + fileName);
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (T s : param) {
                try {
                    bufferedWriter.write(GsonUtil.toJson(s));
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("失败" + "文件名：" + fileName);
            return false;
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }


    private static void sortThreeLevel(Map<String, List<InnerSearchRequest>> innerRequest) {
        System.out.println("-------------------------");
        //Map<String, Map<InnerSearchType, List<InnerSearchRequest>>> innerMapRequest = Maps.newHashMap();

        innerRequest.forEach((k, v) -> {
            System.out.println("接口类型：" + k + "\t数目：" + v.size());
            Map<InnerSearchType, List<InnerSearchRequest>> collect = v.stream().collect(Collectors.groupingBy(InnerSearchRequest::getInner_search_type));
            collect.forEach((k1, v1) -> {
                System.out.println("接口类型：" + k + "\t检索类型：" + k1 + "\t数目：" + v1.size());
                Map<String, List<InnerSearchRequest>> collect1 = mapByUseType(v1);
                collect1.forEach((k2, v2) -> {
                    System.out.println("接口类型：" + k + "\t检索类型：" + k1 + "\t" + k2 + "数目：" + v2.size() + " 占比：" + ((double) v2.size() / (double) v1.size()) * 100);
                    if (!k2.equals("use_new")) {
                        writeExcel(v2, k + "_" + k1 + "_" + k2);
                    }
                });
                System.out.println();
            });
            System.out.println();
            //innerMapRequest.put(k, collect);
        });
        System.out.println("-------------------------");
    }

    class Request {
        InnerSearchRequest first_request;
        DSSSearchCanBookRequest second_request;

        public InnerSearchRequest getFirst_request() {
            return first_request;
        }

        public void setFirst_request(InnerSearchRequest first_request) {
            this.first_request = first_request;
        }

        public DSSSearchCanBookRequest getSecond_request() {
            return second_request;
        }

        public void setSecond_request(DSSSearchCanBookRequest second_request) {
            this.second_request = second_request;
        }
    }

    private static void printCountInfo(Map<String, List<String>> typeRequest) {
        Map<String, Integer> count = Maps.newHashMap();
        typeRequest.forEach((k, v) -> count.put(k, v.size()));

        final int[] total = {0};
        typeRequest.values().forEach((c) -> total[0] += c.size());
        System.out.println("待处理总请求数:" + total[0] + " type count map:" + JSON.toJSONString(count));
    }

    private static List<File> getAllFile() {
        List<File> allFile = Lists.newArrayList();
        File di = new File(BASE_FILE);
        File[] files = di.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().startsWith("gs_request"))
                    continue;
                System.out.println("文件名：" + file.getName() + " 大小：" + file.length() / 1024 / 1024 + "MB");
                allFile.add(file);
            }
        }
        return allFile;
    }

    private static Map<String, List<String>> filterAndClassification(List<String> request, String[] saveType) {
        Map<String, List<String>> typeRequest = Maps.newHashMap();
        request.stream()
                .filter((s) -> {
                    if (s == null || s.length() == 0)
                        return false;
                    for (String s1 : saveType) {
                        if (s.contains(s1)) {
                            return true;
                        }
                    }
                    return false;
                })
                .forEach((s) -> {
                    for (String s1 : saveType) {
                        if (s.contains(s1)) {
                            typeRequest.merge(s1, Lists.newArrayList(s), (b, c) -> {
                                b.addAll(c);
                                return b;
                            });
                            break;
                        }
                    }
                });
        return typeRequest;
    }

    private static List<String> getRequestOfFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        List<String> res = new ArrayList<>();
        BufferedReader bf = new BufferedReader(reader);

        String str = null;
        while ((str = bf.readLine()) != null) {
            res.add(str);
        }
        System.out.println("Name:" + file.getName() + "  Size:" + res.size());
        return res;
    }

}
