package com.product;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities extends Base {
	public static final int wait_time = 2000;

	public void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	public  void sendKeyText(WebDriver driver, WebElement element, String value, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	public void eleWait(WebDriver driver, WebElement element, int timeout)  {
		new WebDriverWait(driver, timeout).ignoring(ElementNotInteractableException.class).until(ExpectedConditions.visibilityOf(element));
	}

	public void visibleWait(WebDriver driver, List<WebElement> element, int timeout)  {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(element));
	} 

	public void forceWait() {
		try {
			Thread.sleep(2000);;
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void sendKeys(By by,String text) {
		try {
			driver.findElement(by).sendKeys(text);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
