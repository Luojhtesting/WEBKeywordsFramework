package org.keywordsFramework.testScripts;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.keywordsFramework.configuration.Constans;
import org.keywordsFramework.configuration.KeyWordsAction;
import org.keywordsFramework.util.DateBaseUtil;
import org.keywordsFramework.util.ExcelUtil;
import org.keywordsFramework.util.Log;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSuiteByExcel {
    public static Method method[];
    public static String keyword;
    public static String value;
    public static String locatorExpression;
    public static KeyWordsAction keyWordsAction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;
    public static boolean testResult;

    //测试用例脚本
    @Test
    public void testSuite() throws Exception {

        //通过反射机制获取keyWordsAction类中的所有方法
        keyWordsAction = new KeyWordsAction();
        method = keyWordsAction.getClass().getMethods();
        //获取Excel路径并生成实例
        String excelPath = Constans.Path_ExcelFile;
        ExcelUtil.setExcelFile(excelPath);
        ExcelUtil.testResultsClear();

        //获取测试用例套件中的测试用例总数
        int testCasesCount = ExcelUtil.getRowCount(Constans.Sheet_TestSuite);
        //遍历拿到测试用例ID与测试用例是否执行判断值
        for(int testCaseNo=1;testCaseNo<=testCasesCount;testCaseNo++) {
            testCaseID = ExcelUtil.getCellData(Constans.Sheet_TestSuite, testCaseNo, Constans.Col_TestCaseID);
            testCaseRunFlag = ExcelUtil.getCellData(Constans.Sheet_TestSuite, testCaseNo, Constans.Col_RunFlag);

            if(testCaseRunFlag.equalsIgnoreCase("y")) {
                Log.startTestCase(testCaseID);
                testResult = true;
                testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constans.Sheet_TestSteps, testCaseID, Constans.Col_TestCaseID);
                testLastStep = ExcelUtil.getTestCaseLastStepRow(Constans.Sheet_TestSteps, testCaseID, testStep);

                //获取测试用例的关键字，定位表达式，操作值
                for (;testStep<testLastStep;testStep++) {
                    keyword = ExcelUtil.getCellData(Constans.Sheet_TestSteps, testStep, Constans.Col_KeyWordAction);
                    Log.info("从Excel文件中读取到的关键字：" + keyword);
                    locatorExpression = ExcelUtil.getCellData(Constans.Sheet_TestSteps, testStep, Constans.Col_LocatorExpression);
                    Log.info("从Excel文件中读取到的定位表达式：" + locatorExpression);
                    value = ExcelUtil.getCellData(Constans.Sheet_TestSteps, testStep, Constans.Col_ActionValue);
                    Log.info("从Excel文件中读取到的操作值：" + value);
                    executeActions();
                    //当用例步骤执行失败后，写入失败信息，执行下个用例
                    if (testResult == false) {
                        ExcelUtil.setCellData(Constans.Sheet_TestSuite, testCaseNo, Constans.Col_TestSuiteTestResult, "测试用例执行失败");
                        break;
                    }
                    //当用例步骤执行成功后，写入成功信息
                    if (testResult == true) {
                        ExcelUtil.setCellData(Constans.Sheet_TestSuite, testCaseNo, Constans.Col_TestSuiteTestResult, "测试用例执行成功");
                    }
                }
                Log.endTestCase(testCaseID);
            }
        }
    }

    //通过keyWordsAction的关键字匹配相对应的方法，传入参数执行方法
    private static void executeActions() {
        try {
            for (int i=0;i<method.length;i++) {
                if (method[i].getName().equals(keyword)) {
                    method[i].invoke(keyWordsAction, locatorExpression, value);
                    if (testResult == true) {
                        ExcelUtil.setCellData(Constans.Sheet_TestSteps, testStep, Constans.Col_TestStepTestResult, "测试用例步骤执行成功");
                        break;
                    } else {
                        ExcelUtil.setCellData(Constans.Sheet_TestSteps, testStep, Constans.Col_TestStepTestResult, "测试用例步骤执行失败");
                        //KeyWordsAction.error_browser("","");
                        break;
                    }

                }
            }
        } catch (Exception e) {
            Assert.fail("测试用例执行失败");
        }
    }

    @BeforeClass
    public void beforeClass() {
        //日志信息打印
        DOMConfigurator.configure("log4j.xml");

    }

    /*@AfterClass
    public void afterClass() {
        DateBaseUtil.getSqlSession();//建立连接
        String[] modelNameArray = {"TestModelCyan","TestModelCyanUpdate"};//删除的模型名称
        String[] materialNameArray = {"TestMaterialCyan","TestMaterialCyanUpdate"};//删除的模型名称
        DateBaseUtil.deleteTestModel(modelNameArray);//执行删除sql
        DateBaseUtil.deleteTestMaterial(materialNameArray);//执行删除sql
        DateBaseUtil.closeSqlSession();//关闭连接
    }*/
}
