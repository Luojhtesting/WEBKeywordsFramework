package org.keywordsFramework.testScripts;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.keywordsFramework.configuration.Constans;
import org.keywordsFramework.configuration.KeyWordsAction;
import org.keywordsFramework.util.DataBaseUtil;
import org.keywordsFramework.util.ExcelUtil;
import org.keywordsFramework.util.FileUtil;
import org.keywordsFramework.util.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
        ExcelUtil.testResultsClear(excelPath);//清理测试用例结果

        //获取测试用例套件中的测试用例总数
        int testCasesCount = ExcelUtil.getRowCount(Constans.SHEET_TEST_SUITE);
        //遍历拿到测试用例ID与测试用例是否执行判断值
        for(int testCaseNo=1;testCaseNo<=testCasesCount;testCaseNo++) {
            testCaseID = ExcelUtil.getCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_TEST_CASE_ID);
            testCaseRunFlag = ExcelUtil.getCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_RUN_FLAG);
            String testCaseRetryCount = ExcelUtil.getCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_RETRY_COUNT);
            int retryCount = Integer.valueOf(testCaseRetryCount.split("\\.")[0]).intValue();

            if(testCaseRunFlag.equalsIgnoreCase("y")) {
                Log.startTestCase(testCaseID);
                testResult = true;
                testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constans.SHEET_TEST_STEPS, testCaseID, Constans.COL_TEST_CASE_ID);
                testLastStep = ExcelUtil.getTestCaseLastStepRow(Constans.SHEET_TEST_STEPS, testCaseID, testStep);

                //获取测试用例的关键字，定位表达式，操作值
                for (;testStep<testLastStep;testStep++) {
                    keyword = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_KEY_WORD_ACTION);
                    Log.info("从Excel文件中读取到的关键字：" + keyword);
                    locatorExpression = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_LOCATOR_EXPRESSION);
                    Log.info("从Excel文件中读取到的定位表达式：" + locatorExpression);
                    value = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_ACTION_VALUE);
                    Log.info("从Excel文件中读取到的操作值：" + value);
                    executeActions(excelPath);
                    //当用例步骤执行失败后，写入失败信息，执行下个用例
                    if (testResult == false) {
                        ExcelUtil.setCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_TEST_SUITE_TEST_RESULT, "测试用例执行失败", excelPath);
                        break;
                    }
                    //当用例步骤执行成功后，写入成功信息
                    if (testResult == true) {
                        ExcelUtil.setCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_TEST_SUITE_TEST_RESULT, "测试用例执行成功", excelPath);
                    }
                }
                Log.endTestCase(testCaseID);
            }
            //失败重试
            failRetry(excelPath, retryCount, testCaseNo);
        }
    }

    //通过keyWordsAction的关键字匹配相对应的方法，传入参数执行方法
    private static void executeActions(String filePath) {
        try {
            for (int i=0;i<method.length;i++) {
                if (method[i].getName().equals(keyword)) {
                    method[i].invoke(keyWordsAction, locatorExpression, value);
                    if (testResult == true) {
                        ExcelUtil.setCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_TEST_STEP_TEST_RESULT, "测试用例步骤执行成功", filePath);
                        break;
                    } else {
                        ExcelUtil.setCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_TEST_STEP_TEST_RESULT, "测试用例步骤执行失败", filePath);
                        break;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //失败重试
    private static void failRetry(String excelPath,int retryCount, int testCaseNo) {
        if (testResult == false && retryCount > 0) {
            for (int num=1;num<=retryCount;num++) {
                Log.retryStartTestCase(testCaseID, num);
                testResult = true;
                testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constans.SHEET_TEST_STEPS, testCaseID, Constans.COL_TEST_CASE_ID);
                testLastStep = ExcelUtil.getTestCaseLastStepRow(Constans.SHEET_TEST_STEPS, testCaseID, testStep);
                for (;testStep<testLastStep;testStep++) {
                    keyword = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_KEY_WORD_ACTION);
                    Log.info("从Excel文件中读取到的关键字：" + keyword);
                    locatorExpression = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_LOCATOR_EXPRESSION);
                    Log.info("从Excel文件中读取到的定位表达式：" + locatorExpression);
                    value = ExcelUtil.getCellData(Constans.SHEET_TEST_STEPS, testStep, Constans.COL_ACTION_VALUE);
                    Log.info("从Excel文件中读取到的操作值：" + value);
                    executeActions(excelPath);
                    //当用例步骤执行失败后，写入失败信息，执行下个用例
                    if (testResult == false) {
                        ExcelUtil.setCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_TEST_SUITE_TEST_RESULT, "测试用例执行失败", excelPath);
                        break;
                    }
                }
                //当用例步骤执行成功后，写入成功信息
                if (testResult == true) {
                    ExcelUtil.setCellData(Constans.SHEET_TEST_SUITE, testCaseNo, Constans.COL_TEST_SUITE_TEST_RESULT, "测试用例执行成功", excelPath);
                    Log.retryEndTestCase(testCaseID, num);
                    break;
                }
                Log.retryEndTestCase(testCaseID, num);
            }
        }
    }

    @BeforeClass
    public void beforeClass() {
        //日志信息打印
        DOMConfigurator.configure("log4j.xml");
        DataBaseUtil.connectionSqlSession();//建立连接
        DataBaseUtil.deleteGoodsByCreaterUser(Constans.CREATE_USER);//删除模型与贴图测试数据
        DataBaseUtil.deleteMaterialByCreaterUser(Constans.CREATE_USER);//删除材质测试数据
        DataBaseUtil.deleteFeedbackData(Constans.FEEDBACK_UID);//删除用户反馈测试数据
        DataBaseUtil.deleteBrandsData(Constans.BRANDS_NAME_ARRAY);//删除品牌测试数据
        DataBaseUtil.deleteUserVersionConfigData(Constans.CREATE_USER);//删除账号版本配置数据
        DataBaseUtil.deleteMapGoodsData(Constans.CREATE_USER);//删除贴图商品数据
        DataBaseUtil.deleteRoleData(Constans.CREATE_ADMIN_USER);//删除角色数据
        FileUtil.createDir(Constans.DOWNLOADS_PATH);//创建文件下载目录
        //WindowsUtils.killByName("chrome.exe");
    }

    @AfterClass
    public void afterClass() {
        DataBaseUtil.deleteBrandsData(Constans.BRANDS_NAME_ARRAY);//删除品牌测试数据
        DataBaseUtil.deleteFeedbackData(Constans.FEEDBACK_UID);//删除用户反馈测试数据
        DataBaseUtil.deleteGoodsByCreaterUser(Constans.CREATE_USER);//删除模型与贴图测试数据
        DataBaseUtil.deleteMaterialByCreaterUser(Constans.CREATE_USER);//删除材质测试数据
        DataBaseUtil.deleteUserVersionConfigData(Constans.CREATE_USER);//删除账号版本配置数据
        DataBaseUtil.deleteMapGoodsData(Constans.CREATE_USER);//删除贴图商品数据
        DataBaseUtil.deleteRoleData(Constans.CREATE_ADMIN_USER);//删除角色数据
        DataBaseUtil.closeSqlSession();//关闭连接
    }
}
