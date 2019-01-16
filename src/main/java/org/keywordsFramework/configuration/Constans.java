package org.keywordsFramework.configuration;

import java.util.Arrays;

public class Constans {
    //测试用例路径
    public static final String Path_ExcelFile = "./src/main/java/org/keywordsFramework/data/关键字驱动测试用例.xlsx";

    //页面元素路径
    public static final String Path_ConfigurationFile = "./ObjectMap.properties";

    //测试数据sheet中的列号常量设定
    public static final int COL_TEST_CASE_ID = 0;

    public static final int COL_KEY_WORD_ACTION = 3;

    public static final int COL_LOCATOR_EXPRESSION = 4;

    public static final int COL_ACTION_VALUE = 5;

    public static final int COL_TEST_STEP_TEST_RESULT = 6;

    public static final int COL_RUN_FLAG = 2;

    public static final int COL_TEST_SUITE_TEST_RESULT = 3;

    public static final String SHEET_TEST_STEPS = "测试用例";

    public static final String SHEET_TEST_SUITE = "测试用例集合";

    //模型接口访问路径
    public static final String BACKSTAGE_MODEL_API_PATH = "http://tumaxapi.to8to.com/admin/goods/model/detail";

    //登陆接口路径
    public static final String BACKSTAGE_LOGIN_PATH = "http://tumaxapi.to8to.com/admin/common/login";

    //创建用户
    public static final String CREATE_USER = "cyan.luo";

    //用户反馈UID
    public static final int FEEDBACK_UID = 1725353;

    //品牌名称
    public static final String[] BRANDS_NAME_ARRAY = {"TestBrandCyanUpdate","TestBrandCyan"};

}
