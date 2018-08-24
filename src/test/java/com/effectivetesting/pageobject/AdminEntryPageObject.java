package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminEntryPageObject {
	private WebDriver driver;	
	
	public AdminEntryPageObject(WebDriver driver) {
	this.driver = driver;
	}
	
	public AdminEntryPageObject deleteEntryNumber () {
		driver.get("http://localhost:5000/admin/entry/?sort=0");
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/form/button/i")).click();
		driver.switchTo().alert().accept();
		return this;
	}

}
