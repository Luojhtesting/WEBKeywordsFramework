package org.keywordsFramework.util;

import org.apache.log4j.Logger;

public class Log {
    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String testCaseName) {
        Log.info("----------------------     " + testCaseName + " " + "开始执行" +"     ----------------------");
    }

    public static void endTestCase(String testCaseName) {
        Log.info("----------------------     " + testCaseName + " " + "执行结束" +"     ----------------------");
    }

    public static void retryStartTestCase(String testCaseName, int retryCount) {
        Log.info("----------------------     " + testCaseName + " " + "失败重试，第" + retryCount +"次     ----------------------");
    }

    public static void retryEndTestCase(String testCaseName, int retryCount) {
        Log.info("----------------------     " + testCaseName + " " + "重试" + retryCount + "次执行结束" + "     ----------------------");
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}

