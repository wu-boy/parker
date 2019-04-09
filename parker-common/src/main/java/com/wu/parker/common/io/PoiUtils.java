package com.wu.parker.common.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * POI工具类
 * @author wusq
 * @date 2019/4/9
 */
public class PoiUtils {

    /**
     * 创建表头
     * @param row
     * @param titles
     */
    public static void createTitle(Row row, String[] titles){
        if(titles == null || titles.length < 1){
            return;
        }
        for(int i=0,len=titles.length; i < len; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
    }

    /**
     * 关闭Workbook
     * @param workbook
     */
    public static void close(Workbook workbook){
        try {
            if(workbook != null){
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
