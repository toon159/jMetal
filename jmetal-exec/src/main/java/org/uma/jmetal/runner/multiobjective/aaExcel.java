package org.uma.jmetal.runner.multiobjective;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class aaExcel {

    private static String FILE_NAME = "result.xlsx";
    ArrayList<double[]> data;

    public aaExcel(ArrayList<double[]> data) {
        this.data = data;
    }

    public void toExcel() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("result");

        int rowNum = 0;
        System.out.println("Creating excel");

        for (double[] obj : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (double value : obj) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(value);
            }
        }

        while (true) {
            try {
                FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
                workbook.write(outputStream);
                workbook.close();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Try creating excel again");
                FILE_NAME += ".xlsx";
            }
        }
        System.out.println("Done");
    }

}