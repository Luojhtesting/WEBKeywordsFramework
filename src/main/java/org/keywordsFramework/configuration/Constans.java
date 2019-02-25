package org.keywordsFramework.configuration;

import java.util.Arrays;

public class Constans {
    //超级后台测试用例路径
    public static final String Path_ExcelFile = "./src/main/java/org/keywordsFramework/data/关键字驱动测试用例-超级后台.xlsx";

    //商家后台测试用例路径
    public static final String PATH_EXCELFILE_MERCHANT = "./src/main/java/org/keywordsFramework/data/关键字驱动测试用例-商家后台.xlsx";

    //页面元素路径
    public static final String Path_ConfigurationFile = "./ObjectMap.properties";

    //测试数据sheet中的列号常量设定
    public static final int COL_TEST_CASE_ID = 0;

    //关键字参数
    public static final int COL_KEY_WORD_ACTION = 3;

    //失败重试参数
    public static final int COL_RETRY_COUNT = 3;

    //测试步骤元素表达式
    public static final int COL_LOCATOR_EXPRESSION = 4;

    //测试步骤入参
    public static final int COL_ACTION_VALUE = 5;

    //测试步骤执行结果
    public static final int COL_TEST_STEP_TEST_RESULT = 6;

    //是否执行标识
    public static final int COL_RUN_FLAG = 2;

    //用例执行结果
    public static final int COL_TEST_SUITE_TEST_RESULT = 4;

    //Sheet页测试用例名称
    public static final String SHEET_TEST_STEPS = "测试用例";

    //Sheet页测试套件名称
    public static final String SHEET_TEST_SUITE = "测试用例集合";

    //模型接口访问路径
    public static final String BACKSTAGE_MODEL_API_PATH = "http://tumaxapi.to8to.com/admin/goods/model/detail";

    //登陆接口路径
    public static final String BACKSTAGE_LOGIN_PATH = "http://tumaxapi.to8to.com/admin/common/login";

    //创建用户
    public static final String CREATE_USER = "cyan.luo";

    //Admin表创建用户
    public static final String CREATE_ADMIN_USER = "cyan_luo";

    //用户反馈UID
    public static final int FEEDBACK_UID = 1725353;

    //品牌名称
    public static final String[] BRANDS_NAME_ARRAY = {"TestBrandCyanUpdate","TestBrandCyan"};

    //ChromeDriver下载地址
    public static final String DOWNLOADS_PATH= "F:/Downloads";

}
