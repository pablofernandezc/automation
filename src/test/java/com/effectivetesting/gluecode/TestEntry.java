package com.effectivetesting.gluecode;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.effectivetesting.pageobject.LoginPageObject;

//import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import cucumber.api.junit.Cucumber;


public class TestEntry {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Given("^the visitor$")
	public void the_visitor() throws Throwable {
		loginPage = new LoginPageObject(driver);
		loginPage
				.login("admin1@gmail.com", "admin1")
				.goToCreateEntry()
				.createEntry("Dummy entry", "this is the text");
		driver.findElement(By.xpath("//*[@id=\"logout\"]/a")).click();
		}
	
	@When("^goes to entry section$")
	public void goes_to_entry_section() throws Throwable {
		driver.get("http://localhost:5000/entries/");
	}
	
	@Then("^he sees the entry \"([^\"]*)\"$")
	public void he_sees_the_entry(String arg1) throws Throwable {
		String foundEntry = driver.findElement(By.xpath("//*[@id=\"content_title\"]/p[1]/a")).getText();
		assertTrue(foundEntry.contains("Dummy entry"));
	}
	
	
	
	
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("localhost:5000");	
	}
	
	@After
	public void teardDown() {
		/*EL ADMIN SE LOGEA*/
		loginPage.login("admin1@gmail.com", "admin1");
		/*ENTRA A LAS ENTRIES ORDENANDAS DE MAS NUEVA A MAS VIEJA*/
		driver.get("http://localhost:5000/admin/entry/?sort=0");
		/*BORRA EL POST*/
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/form")).click();
		/*CONFIRMA BORRAR*/
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    alert.accept();
	    /*SE BORRO EL POST*/
	    WebDriverWait waitForMessage = new WebDriverWait(driver, 10);
	    waitForMessage.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]")));
	    
	    driver.quit();
	}
}
