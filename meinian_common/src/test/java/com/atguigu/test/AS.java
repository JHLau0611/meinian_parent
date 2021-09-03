package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.UUID;

/**
 * @author JHLau
 * @create 2021-08-25 15:13
 */
public class AS {
    public static void main(String[] args) throws IOException {


        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("C:\\Users\\JHLau\\Desktop\\新建 Microsoft Excel 工作表.xlsx");
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (Row row : xssfSheet) {
            for (Cell cell : row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(cell.getStringCellValue());
            }
        }


//        System.out.println(UUID.randomUUID().toString());
    }
}

