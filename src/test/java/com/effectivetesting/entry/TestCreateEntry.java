package com.effectivetesting.entry;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateEntry {
	private WebDriver driver;

	@Test
	public void postIsSuccessfull() {
		driver.findElement(By.id("create_post")).click();
		driver.findElement(By.id("title")).sendKeys("My newest post");
		driver.findElement(By.id("body")).sendKeys("This is a post.");
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/div[5]/div/button")).click();
		
		String currentMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/span")).getText();
		System.out.println(currentMessage);
		
		assertTrue(currentMessage.contains("Entry 'My newest post' created successfully."));
	}
	
	@Test
	public void checkDuplicated() {
		driver.get("localhost:5000/admin/entry/");
		String lastPost = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[3]")).getText();
		assertTrue(lastPost.contains("My newest post"));
	}
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("localhost:5000");
		
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
	}

	@After
	public void teardDown() {
		driver.quit();
		driver.get("localhost:5000/admin/entry/");
		
	}
}
