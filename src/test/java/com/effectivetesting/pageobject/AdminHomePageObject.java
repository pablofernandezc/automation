package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHomePageObject {

	private WebDriver driver;
	
	public AdminHomePageObject(WebDriver driver){
		this.driver = driver;
	}
	public AdminEntryPageObject goToEntrySection () {
		driver.findElement(By.xpath("//*[@id=\"blog\"]/a")).click();
		return new AdminEntryPageObject(driver);
	}
}
