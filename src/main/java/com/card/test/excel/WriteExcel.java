package com.card.test.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihp on 2020/6/5.
 */
public class WriteExcel {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) {
        ArrayList<String> head = Lists.newArrayList("Name", "Age", "Phone");
        List<List<String>> dataList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            ArrayList<String> strings = Lists.newArrayList("Name" + i, "Age" + i, "Phone" + i);
            dataList.add(strings);
        }
        writeExcel(head,dataList,"/Users/cuihp/Desktop/1.xlsx");
    }


    public static void writeExcel(List<String> head,List<List<String>> dataList, String finalXlsxPath){

        OutputStream out = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = workbook.createSheet("sheet0");
        try {
            // 获取总列数
            // 读取Excel文档
            Row rowFirst = xssfSheet.createRow(0);
            for (int i = 0; i < head.size(); i++) {
                Cell first = rowFirst.createCell(i);
                first.setCellValue(head.get(i));
            }
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = xssfSheet.createRow(j + 1);
                // 得到要插入的每一条记录
                List<String> list = dataList.get(j);
                for (int i = 0; i < list.size(); i++) {
                    // 在一行内循环
                    Cell first = row.createCell(i);
                    first.setCellValue(list.get(i));
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(new File(finalXlsxPath));
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("数据导出成功");
    }


    private static Workbook getWorkbook(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}



