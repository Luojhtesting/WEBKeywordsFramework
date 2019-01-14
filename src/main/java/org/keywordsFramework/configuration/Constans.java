package org.keywordsFramework.configuration;

public class Constans {
    //测试用例路径
    public static final String Path_ExcelFile = "./src/main/java/org/keywordsFramework/data/关键字驱动测试用例.xlsx";

    //页面元素路径
    public static final String Path_ConfigurationFile = "./ObjectMap.properties";

    //测试数据sheet中的列号常量设定
    public static final int Col_TestCaseID = 0;

    public static final int Col_KeyWordAction = 3;

    public static final int Col_LocatorExpression = 4;

    public static final int Col_ActionValue = 5;

    public static final int Col_TestStepTestResult = 6;

    public static final int Col_RunFlag = 2;

    public static final int Col_TestSuiteTestResult = 3;

    public static final String Sheet_TestSteps = "测试用例";

    public static final String Sheet_TestSuite = "测试用例集合";

    //模型接口访问路径
    public static final String Backstage_Model_API_Path = "http://tumaxapi.to8to.com/admin/goods/model/detail";

    //登陆接口路径
    public static final String Backstage_Login_Path = "http://tumaxapi.to8to.com/admin/common/login";

    //创建用户
    public static final String CREATE_USER = "cyan.luo";

}
