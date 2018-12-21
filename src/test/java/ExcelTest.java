import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.keywordsFramework.configuration.Constans;
import org.keywordsFramework.util.ExcelUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {


    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("E:/T.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);

        FileOutputStream outFile = new FileOutputStream("E:/T.xlsx");
        workbook.write(outFile);
        outFile.flush();
        outFile.close();


    }
}
