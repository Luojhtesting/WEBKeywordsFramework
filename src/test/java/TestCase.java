import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.keywordsFramework.util.KeyBoardUtil.*;

public class TestCase {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "./tools/chromedriver.exe");
        WebDriver dr = new ChromeDriver();
        dr.get("http://flashadmin.to8to.com/admin/login");
        dr.manage().window().maximize();
        dr.findElement(By.id("user")).sendKeys("admin3d");
        dr.findElement(By.id("password")).sendKeys("abcd1234test");
        dr.findElement(By.name("submit")).click();
        dr.get("http://tumaxadmin.to8to.com/#/material/modellist");
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[3]/div[1]/div[2]/button[1]")).click();
        Thread.sleep(1000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[1]/div/div[1]/input")).sendKeys("CyanTestModel1");
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[2]/div/div/div/div/div[1]")).click();
        Thread.sleep(2000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[2]/div/div/div/div/div[2]/div[2]/ul/li[5]/a")).click();
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[2]/div/div/div/div/div[2]/div[2]/ul/li[3]/a")).click();
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[2]/div/div/div/div/div[2]/div[2]/ul/li[3]/a")).click();
        Thread.sleep(1000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[4]/div/div/div/div/span[1]/label/span[2]")).click();
        Thread.sleep(1000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[5]/div/div/div/div/span[1]/label/span[2]")).click();
        Thread.sleep(1000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[7]/div/div/div/div/span[1]/label/span[2]")).click();
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[8]/div/div/div/span/span")).click();
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[8]/div/div/div/span/div/div[1]/input")).sendKeys("TestSpecifications");
        pressEnterKey();
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[2]/form/div[9]/ul/li/div[2]/div/div[1]/div/div/span")).click();
        Thread.sleep(2000);
        setAndCtrlVClipboardData("C:\\Program Files\\Git\\workspace\\WEBKeywordsFramework\\model\\书桌 -标准.rar");
        pressEnterKey();
        Thread.sleep(5000);
        dr.findElement(By.xpath("/html/body/div/div[2]/main/section/section/div/div[1]/div[2]/span[1]")).click();
        Thread.sleep(2000);
        dr.getPageSource().contains("CyanTestModel-false");
    }
}
