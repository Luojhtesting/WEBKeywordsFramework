import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExcelTest {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("E:/T.xlsx");
        XSSFWorkbook ExcelWorkBook = new XSSFWorkbook(file);
        XSSFSheet sheet = ExcelWorkBook.getSheet("Sheet1");
        XSSFCell cell = sheet.getRow(0).getCell(0);
        String s = cell.getCellTypeEnum() == STRING ? cell.getStringCellValue() : cell.getNumericCellValue() + "";

    }
}
