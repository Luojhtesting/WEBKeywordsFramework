package org.keywordsframework.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUitl {
	public static void sleep(long s) {
		try {
			Thread.sleep(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void waitWebElement(WebDriver dr, String string) {
		WebDriverWait wait = new WebDriverWait(dr, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(string)));	
	}
	
	public static void waitWebElement(WebDriver dr, By by) {
		WebDriverWait wait = new WebDriverWait(dr, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));	
	}
}
