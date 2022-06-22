package com.product;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class SearchProduct extends Base {
	Utilities util = new Utilities();
	public static ExtentTest logger;

	@FindBy(xpath="//input[@name=\"q\"]")
	WebElement enterProduct;

	@FindBy(xpath="//span[@class=\"_10Ermr\"]")
	WebElement productResults;

	@FindBy(xpath="//div//button[@class=\"_2KpZ6l _2doB4z\"]")
	WebElement closeButton;

	@FindBy(xpath="//button[@type=\"submit\"]")
	WebElement searchButton;

	@FindBy(xpath="//div[contains(@class,'_2kHMtA')]//following::div[contains(@class,'_4rR01T')]")
	List<WebElement> productNames;

	@FindBy(xpath="//a[contains(@class,'ge-49M')]")
	List<WebElement> paginationList;

	public void search_Products() throws InterruptedException {
		PageFactory.initElements(driver, this);
		driver.get(prop.getProperty("url"));
		util.eleWait(driver, closeButton, 20);
		closeButton.click();
		enterProduct.sendKeys(prop.getProperty("productname"));
	}

	public List<WebElement> list_searchproducts() throws InterruptedException {
		util.clickOn(driver, searchButton, 20);
		util.visibleWait(driver, productNames, 20);
		String searchResults = productResults.getText();
		System.out.println(searchResults);

		if(productNames.size()>0) {			
			logger = extent.createTest("Products Displayed");
			logger.log(Status.PASS, "Count of the Product: " +productNames.size());
			logger.log(Status.INFO, "Results: "+searchResults);
			for(int i=0; i<productNames.size(); i++) {
				System.out.println(productNames.get(i).getText());
				logger.log(Status.INFO, "Name of the Products: " +productNames.get(i).getText());
			}
		}else {
			logger.log(Status.FAIL, "No Products displayed");
		}

		for(int i=0; i<paginationList.size(); i++) {
			util.forceWait();
			paginationList.get(i).click();
		}
		try {
			util.visibleWait(driver, productNames, 30);
			productNames.get(0).click();
		}catch(StaleElementReferenceException e) {
			util.visibleWait(driver, productNames, 30);
			productNames = driver.findElements(By
					.xpath("//div[contains(@class,'_2kHMtA')]//following::div[contains(@class,'_4rR01T')]"));
			productNames.get(0).click();

		}
		return productNames;
	}
}
