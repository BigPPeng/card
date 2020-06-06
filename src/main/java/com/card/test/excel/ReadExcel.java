package com.card.test.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

/**
 * Created by cuihp on 2020/3/12.
 */
public class ReadExcel {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String url = "http://dashboard2.mis.elong.com/proxy/192.168.110.233:8080/getCtripPromotionToSubsidize?activityId=";
    public static void main(String[] args) {
        List<ExcelModel> excelModels = ExcelReader.readExcel("/Users/cuihp/Desktop/all_city_info.xlsx");

        String urlPro = "http://hotel-goods.dictionary.api.vip.elong.com/dict/dim/province/all";
        String urlCity = "http://hotel-goods.dictionary.api.vip.elong.com/dict/dim/city/0800";

        for (ExcelModel excelModel : excelModels) {
            String activity_id = excelModel.getActivity_id();
            if (Strings.isNullOrEmpty(excelModel.getLeft_value1())) {
                System.out.println("excel表格数据Left_value1为空 activityID："+activity_id);
            }
            String URL = url + activity_id;
            String forObject = restTemplate.getForObject(URL, String.class);
            if (forObject == null || forObject.isEmpty()) {
                System.out.println("内存数据不存在：activityID："+activity_id);
                continue;
            }
            HongBaoActivityInfo info = null;
            try {
                info = JSON.parseObject(forObject, HongBaoActivityInfo.class);
            } catch (Exception e) {
                System.out.println("内存解析数据格式化异常:\t元数据："+forObject+"\t excel数据："+excelModel.toString());
            }
            if (info == null) {
                System.out.println("内存数据不存在：activityID："+activity_id);
                continue;
            }
            long groupId = info.getGroupId();
            if (!String.valueOf(groupId).equals(excelModel.getLeft_value1().trim())) {
                System.out.println("内存与Excel数据不一致：activityID："+activity_id+" \t excel:"+excelModel.getLeft_value1()+" \t内存："+info.getGroupId());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");

    }
}

class HongBaoActivityInfo{
    private int activityId;// 红包活动ID
    private boolean isTying;// 是否搭售红包
    private boolean isSale;// 是否售卖红包
    private int costBearingProject;// 成单成本事业部

    /**
     * 优惠类型
     * 满减0、折扣1、特价2、满返3、普通101、同程满减4
     */
    private int discountType;

    /**
     * 国际酒店供应商
     */
    private Set<String> otaIdList;
    private boolean hourProductHongBao;//钟点房红包标志

    /**
     * 活动分组ID
     */
    private long groupId;
    /**
     * 投放渠道
     */
    private long putChannel;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public boolean isTying() {
        return isTying;
    }

    public void setTying(boolean tying) {
        isTying = tying;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public int getCostBearingProject() {
        return costBearingProject;
    }

    public void setCostBearingProject(int costBearingProject) {
        this.costBearingProject = costBearingProject;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public Set<String> getOtaIdList() {
        return otaIdList;
    }

    public void setOtaIdList(Set<String> otaIdList) {
        this.otaIdList = otaIdList;
    }

    public boolean isHourProductHongBao() {
        return hourProductHongBao;
    }

    public void setHourProductHongBao(boolean hourProductHongBao) {
        this.hourProductHongBao = hourProductHongBao;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getPutChannel() {
        return putChannel;
    }

    public void setPutChannel(long putChannel) {
        this.putChannel = putChannel;
    }

    @Override
    public String toString() {
        return "HongBaoActivityInfo{" +
                "activityId=" + activityId +
                ", isTying=" + isTying +
                ", isSale=" + isSale +
                ", costBearingProject=" + costBearingProject +
                ", discountType=" + discountType +
                ", otaIdList=" + otaIdList +
                ", hourProductHongBao=" + hourProductHongBao +
                ", groupId=" + groupId +
                ", putChannel=" + putChannel +
                '}';
    }
}