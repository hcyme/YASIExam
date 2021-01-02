package com.hcy.utils;

import com.hcy.entity.Question;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public HSSFSheet export(HSSFWorkbook wb, String fileName, String[] title,
                            ArrayList<Question> content) {
        // int cellNum = 5; //设置列数
        /**
         * 建立表格设置。
         */
        HSSFSheet sheet = null;
        try {
            sheet = wb.createSheet(fileName);

            int columnSize = title.length;
            HSSFRow row_one = sheet.createRow(0);
            for (int i = 0; i < columnSize; i++) {
                HSSFCell cell = row_one.createCell(i);
                cell.setCellValue(title[i]);
            }
            int curRow = 1;
            int endRow = 1;
            int temp = curRow;
            for (int i = 0; i < content.size(); i++) {
                endRow = i;
                Question shiti = content.get(i);
                for (; curRow < endRow; curRow++) {
                    // 设置需要合并的单元格部分
                    for (int k = 0; k < 7; k++) {
                        CellRangeAddress cra = new CellRangeAddress(curRow,
                                endRow - 1, k, k);
                        sheet.addMergedRegion(cra);
                    }
                    HSSFRow row = sheet.createRow(curRow);
//                    if (curRow == temp) {
//                        row.createCell(0).setCellValue(shiti.getQ_id());
                        row.createCell(0).setCellValue(i);
                        row.createCell(1).setCellValue(shiti.getQ_type());
                        row.createCell(2)
                                .setCellValue(shiti.getQ_title());
                        row.createCell(3).setCellValue(shiti.getQ_select());
                        row.createCell(4).setCellValue(shiti.getQ_answer());
                        row.createCell(5).setCellValue(shiti.getQ_score());
                        row.createCell(6).setCellValue(shiti.getQ_explaination());
//                    }

                }
//                curRow = endRow;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sheet;
    } // end of export

}
