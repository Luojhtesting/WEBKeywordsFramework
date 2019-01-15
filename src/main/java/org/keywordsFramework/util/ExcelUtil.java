package org.keywordsFramework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.keywordsFramework.configuration.Constans;
import org.keywordsFramework.testScripts.TestSuiteByExcel;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExcelUtil {
    private static XSSFWorkbook ExcelWorkbook;
    private static XSSFSheet ExcelSheet;
    private static XSSFRow Row;
    private static XSSFCell Cell;


    //获取Excel文件路径
    public static void setExcelFile(String path) throws Exception {
        FileInputStream file;
        try {
            file = new FileInputStream(path);
            ExcelWorkbook = new XSSFWorkbook(file);
            file.close();
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            System.out.println("Excel路径获取失败");
            e.printStackTrace();
        }
    }

    //读取指定Sheet中的单元格函数，只支持.xlsx
    public static String getCellData(String sheetName, int rowN, int cellN) {

        ExcelSheet = ExcelWorkbook.getSheet(sheetName);

        try {
            Cell = ExcelSheet.getRow(rowN).getCell(cellN);
            String cellData = Cell.getCellTypeEnum() == STRING ? Cell.getStringCellValue()
                    : Cell.getNumericCellValue() + "";
            return cellData;
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
            return "";
        }
    }

    //知道获取Sheet中的数据总行数
    public static int getRowCount(String SheetName) {
        ExcelSheet = ExcelWorkbook.getSheet(SheetName);
        int number = ExcelSheet.getLastRowNum();
        return number;
    }

    //在Excel的指定Sheet中，获取第一次包含指定测试用例序号文字的行号
    public static int getFirstRowContainsTestCaseID(String sheetName,String testCaseName,int colNum) {
        int i;
        try {
            ExcelSheet = ExcelWorkbook.getSheet(sheetName);
            int rowCont = ExcelUtil.getRowCount(sheetName);
            for (i=0;i<rowCont;i++){
                if (ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)) {
                    break;
                }
            }
            //System.out.println(i);
            return i;
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            return 0;
        }
    }

    //获取测试用例末尾步骤数
    public static int getTestCaseLastStepRow(String sheetName,String testCaseID, int testCaseStartRowNumber) {
        try {
            ExcelSheet = ExcelWorkbook.getSheet(sheetName);
            for (int i=testCaseStartRowNumber;i<=ExcelUtil.getRowCount(sheetName)-1;i++) {
                if(!testCaseID.equals(ExcelUtil.getCellData(sheetName, i, Constans.COL_TEST_CASE_ID))) {
                    int number = i;
                    //System.out.println(number);
                    return number;
                }
            }
            int number = ExcelSheet.getLastRowNum()+1;
            return number;
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
            return 0;
        }
    }

    //在Excel文件指定的Sheet页写入数据
    public static void setCellData(String sheetName,int rowNum, int cellNum, String result) {
        try {
            ExcelSheet = ExcelWorkbook.getSheet(sheetName);
            XSSFCellStyle cellStyle = ExcelWorkbook.createCellStyle();;
            cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
            cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
            cellStyle.setBorderTop(BorderStyle.THIN);//上边框
            cellStyle.setBorderRight(BorderStyle.THIN);//右边框
            Row = ExcelSheet.getRow(rowNum);
            Cell = Row.getCell((short)cellNum);

            if(Cell == null) {
                Cell = Row.createCell(cellNum);
                Cell.setCellStyle(cellStyle);
                Cell.setCellValue(result);
            } else {
                Cell.setCellStyle(cellStyle);
                Cell.setCellValue(result);
            }
            FileOutputStream fileOut = new FileOutputStream(Constans.Path_ExcelFile);
            ExcelWorkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    //在Excel文件中获取测试数据的静态方法
    public static Object[][] getTestData(String excelFilePath, String sheetName) throws IOException {
        File file = new File(excelFilePath);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook Workbook = null;

        //获取文件扩展名，判断.xlsx还是.xls
        String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));

        if (fileExtensionName.equals(".xlsx")) {
            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            Workbook = new HSSFWorkbook(inputStream);
        }

        Sheet Sheet = Workbook.getSheet(sheetName);

        //计算Excel文件中有多少行
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();

        List<Object[]> records = new ArrayList<Object[]>();

        for (int i=0;i<rowCount;i++) {
            Row row = Sheet.getRow(i);
            String fields[] = new String[row.getLastCellNum()];
            for (int j=0;j<row.getLastCellNum();j++) {
                fields[j] = (String)(row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING ?
                        row.getCell(j).getStringCellValue() :
                        "" + row.getCell(j).getNumericCellValue());
            }
            records.add(fields);
        }

        Object[][] results = new Object[records.size()][];
        for(int i=0;i<records.size();i++) {
            results[i] = records.get(i);
        }

        return results;

    }

    //测试结果清除
    public static void testResultsClear() {
        //suite清理
        for (int i=1;i<ExcelUtil.getRowCount(Constans.SHEET_TEST_SUITE)+1;i++) {
            ExcelUtil.setCellData(Constans.SHEET_TEST_SUITE,i,Constans.COL_TEST_SUITE_TEST_RESULT,"");
        }

        //步骤清理
        for (int i=1;i<ExcelUtil.getRowCount(Constans.SHEET_TEST_STEPS)+1;i++) {
            ExcelUtil.setCellData(Constans.SHEET_TEST_STEPS,i,Constans.COL_TEST_STEP_TEST_RESULT,"");
        }
    }

    //


}
