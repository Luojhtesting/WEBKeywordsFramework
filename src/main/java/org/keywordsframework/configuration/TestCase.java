package org.keywordsframework.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", 
				"D:\\Users\\CYAN.LUO\\eclipse-workspace\\keywordFramework\\Tools\\chromedriver.exe");
		WebDriver dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get("http://www.to8to.com/com_login.php");
		dr.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("cyan_luo");;
		dr.findElement(By.xpath("//*[@id=\"userNum\"]")).sendKeys("to8to123");;
		dr.findElement(By.xpath("//*[@id=\"reg_form\"]/input[3]")).click();
		Thread.sleep(5000);
		dr.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/i")).click();
		Thread.sleep(2000);
		dr.get("http://www.to8to.com/my/zxgs_zhuanchu.php");
		Thread.sleep(1000);
		dr.findElement(By.id("pay_password")).click();
		
		
	}

}
