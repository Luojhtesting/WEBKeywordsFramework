package org.keywordsFramework.configuration;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.keywordsFramework.testScripts.TestSuiteByExcel;
import org.keywordsFramework.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import static org.keywordsFramework.util.InterfaceAction.createFeedbackResult;
import static org.keywordsFramework.util.InterfaceAction.getAuth;

public class KeyWordsAction {
    public static WebDriver dr;
    private static ObjectMap objectMap = new ObjectMap(Constans.Path_ConfigurationFile);
    static {
        DOMConfigurator.configure("log4j.xml");
    }

    //选择浏览器
    public static void openBrowser(String locatorExpression,String browser) {
        try {
            if(browser.equals("ie")) {
                dr = new InternetExplorerDriver();
                Log.info("IE浏览器启动成功");
            } else if(browser.toLowerCase().equals("firefox")) {
                System.setProperty("webdriver.gecko.driver",
                        "./tools/geckodriver.exe");
                dr = new FirefoxDriver();
                dr.manage().window().maximize();
                dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Log.info("firefox浏览器启动成功");
            } else if(browser.toLowerCase().equals("chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        "./tools/chromedriver.exe");
                DesiredCapabilities caps = setDownloadsPath();
                dr = new ChromeDriver(caps);
                dr.manage().window().maximize();
                dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Log.info("chrome浏览器启动成功");
            } else {
                System.out.println("只支持“IE”、“firefox”、“chrome”浏览器");
            }
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    //url路径
    public static void navigate(String locatorExpression,String url) {
        try {
            dr.get(url);
            Log.info("地址：" + url + "打开成功");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("地址：" + url + "打开失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //单击
    public static void click(String locatorExpression, String string) {
        try {
            dr.findElement(objectMap.getLocator(locatorExpression)).click();
            Log.info("单击" + locatorExpression + "页面元素");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("单击" + locatorExpression + "页面元素失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //输入
    public static void input(String locatorExpression, String string) {
        try {
            dr.findElement(objectMap.getLocator(locatorExpression)).clear();
            dr.findElement(objectMap.getLocator(locatorExpression)).sendKeys(string);
            Log.info("在" + locatorExpression + "元素中，输入：" + string);
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("在" + locatorExpression + "元素中，输入：" + string + "失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //转入Iframe
    public static void switchToIframe(String locatorExpression,String id) {
        try {
            dr.switchTo().frame(id);
            Log.info("转入ID：" + id + "Iframe页面成功");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("转入ID：" + id + "Iframe页面失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //等待时间
    public static void sleep(String locatorExpression,String sleepTime) {
        try {
            WaitUitl.sleep(Integer.parseInt(sleepTime));
            Log.info("休眠：" + Integer.parseInt(sleepTime)/1000 + "秒");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("休眠异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //转出Iframe
    public static void turnOutIframe(String locatorExpression,String string) {
        try {
            dr.switchTo().defaultContent();
            Log.info("转出Iframe");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("转出Iframe异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //键盘Ctrl+V复制数据
    public static void pressCtrlV(String locatorExpression,String string) {
        try {
            KeyBoardUtil.setAndCtrlVClipboardData(string);
            Log.info("使用剪切板复制数据：" + string);
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("使用剪切板复制数据异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //键盘Tab
    public static void pressTab(String locatorExpression,String string) {
        try {
            KeyBoardUtil.pressTabKey();
            Log.info("按下Tab键");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("按下Tab键异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //按下Enter键
    public static void pressEnter(String locatorExpression,String string) {
        try {
            KeyBoardUtil.pressEnterKey();
            Log.info("按下Enter键");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("按下Enter键异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //点击发送
    public static void clickSendMailButton(String locatorExpression,String string) {
        try {
            List<WebElement> buttons = dr.findElements(By.xpath("//*[@id=\"toolbar\"]/div/a[1]"));
            buttons.get(0).click();
            Log.info("单击发送按钮");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("单击发送按钮异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //页面包含字符串
    public static void assertContainString(String locatorExpression,String assertString) {
        try {
            Assert.assertTrue(dr.getPageSource().contains(assertString));
            Log.info("断言成功");
        } catch (AssertionError e) {
            TestSuiteByExcel.testResult = false;
            Log.info("断言异常，具体异常信息：" + e.getMessage());
        }
    }

    //页面不包含字符串
    public static void assertNotContainString(String locatorExpression,String assertString) {
        try {
            Assert.assertFalse(dr.getPageSource().contains(assertString));
            Log.info("断言成功");
        } catch (AssertionError e) {
            TestSuiteByExcel.testResult = false;
            Log.info("断言异常，具体异常信息：" + e.getMessage());
        }
    }

    //关闭浏览器
    public static void closeBrowser(String locatorExpression,String string) {
        try {
            dr.quit();
            Log.info("关闭浏览器");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("关闭浏览器异常，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //异常关闭浏览器
    public static void errorBrowser(String locatorExpression,String string) {
        try {
            dr.quit();
            Log.error("关闭浏览器");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    //截图
    public static void takesScreenhot(String locatorExpression,String TestCaseName) {
        try {
            Date date = new Date();
            String picDir = "E:\\" + TestCaseName + "\\" + String.valueOf(DateUtil.getYear(date))
                    + "-" + String.valueOf(DateUtil.getMonth(date))
                    + "-" + String.valueOf(DateUtil.getDay(date));
            if(!new File(picDir).exists()) {
                FileUtil.createDir(picDir);
            }

            String filepath = picDir + "\\"+ String.valueOf(DateUtil.getHour(new Date()))
                    + "-" + String.valueOf(DateUtil.getMinute(new Date()))
                    + "-" + String.valueOf(DateUtil.getSecond(new Date())) + ".png";
            File srcFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filepath));
            Log.info("截取图片成功，所在路径：" + filepath);
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("截取图片失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //获取元素文本，断言类容
    public static void getElementTextAssert(String locatorExpression,String assertString) {
        try {
            String elementText = dr.findElement(objectMap.getLocator(locatorExpression)).getText();
            if (assertString.equals(elementText)) {
                Log.info("断言成功");
            } else {
                TestSuiteByExcel.testResult = false;
                Log.info("断言结果：" + assertString + "!=" + elementText);
            }

        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("异常信息：" + e.getMessage());
        }
    }

    //模型渲染状态
    public static void concludeModelApplyState(String locatorExpression,String modelName) {
        try {
            int modelId = DateBaseUtil.selectModelId(modelName);

            for (int i=0;i<3;i++) {
                if (InterfaceAction.getModelRenderState(modelId) == 2) {
                    Log.info("模型渲染完成");
                    break;
                } else if (InterfaceAction.getModelRenderState(modelId) == 1) {
                    Thread.sleep(300000);
                } else if (InterfaceAction.getModelRenderState(modelId) == 0) {
                    Thread.sleep(300000);
                } else {
                    TestSuiteByExcel.testResult = false;
                    Log.info("渲染失败");
                    break;
                }
            }

            if (InterfaceAction.getModelRenderState(modelId) != 2) {
                TestSuiteByExcel.testResult = false;
                Log.info("渲染失败");
            }

        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("具体异常信息：" + e.getMessage());
        }
    }

    //元素中包含字符串
    public static void assertElementContainString(String locatorExpression,String assertString) {
        try {
            String[] arr = assertString.split(",");

            for (String s : arr) {
                dr.findElement(objectMap.getLocator(locatorExpression)).getText().contains(s);
                Log.info("元素中包含" + s + "字符");
            }
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("异常信息：" + e.getMessage());
        }
    }

    //滚动条滚动到顶端
    public static void scrollBarTop(String locatorExpression,String string) {
        try {
            ((JavascriptExecutor) dr).executeScript("window.scrollTo(0, 0)");
            Log.info("滚动条移动到顶端");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("断言异常，具体异常信息：" + e.getMessage());
        }

    }

    //滚动条滚动到底部
    public static void scrollBarBottom(String locatorExpression,String string) {
        try {
            ((JavascriptExecutor) dr).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Log.info("滚动条移动到底部");
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("断言异常，具体异常信息：" + e.getMessage());
        }

    }

    //创建用户反馈测试数据
    public static void createFeedback(String locatorExpression,String uid) {
        try {
            int i = Integer.parseInt(uid);
            if (InterfaceAction.createFeedbackResult(InterfaceAction.getAuth(i))) {
                Log.info("创建用户反馈测试数据成功");
            } else {
                TestSuiteByExcel.testResult = false;
                Log.info("创建用户反馈测试数据失败");
            }
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("创建失败信息：" + e.getMessage());
        }
    }

    //单个输入字符串
    public static void forSendKeys(String locatorExpression,String string) {
        try {
            dr.findElement(objectMap.getLocator(locatorExpression)).clear();
            for (int i = 0; i < string.length(); i++) {
                dr.findElement(objectMap.getLocator(locatorExpression)).sendKeys(string.charAt(i)+"");
            }
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("在" + locatorExpression + "元素中，输入：" + string + "失败，具体异常信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //导出文件与页面数据总数比对
    public static void exportDataContrast(String locatorExpression, String path) {
        try {
            String filePath = path.split(">")[0];
            String sheetPath = path.split(">")[1];
            int excelCount = ExcelUtil.getSheetCount(filePath, sheetPath);
            int pageCount = Integer.parseInt(dr.findElement(objectMap.getLocator(locatorExpression)).getText().replaceAll("\\D",""));
            if (excelCount == pageCount) {
                Log.info("比对信息：" + excelCount + "=" + pageCount);
            } else {
                throw new Exception(excelCount + "!=" + pageCount);
            }
        } catch (Exception e) {
            TestSuiteByExcel.testResult = false;
            Log.info("比对失败异常信息：" + e.getMessage());
        }
    }

    //获取ChromeDriver下载地址配置
    private static DesiredCapabilities setDownloadsPath() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", Constans.DOWNLOADS_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return caps;
    }
}
