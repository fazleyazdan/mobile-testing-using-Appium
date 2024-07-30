package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class GoogleBrowsingPage {

	AndroidDriver driver;
	
	// constructor
	public GoogleBrowsingPage(AndroidDriver d)
	{
		driver = d;
		PageFactory.initElements(driver, this);
		
	}
	
	WebElement searchBox = driver.findElement(By.name("q"));
	public void typeInSearchBox(String searchData)
	{
		
		searchBox.sendKeys(searchData);
	}
	
	public void pressEnter()
	{
		searchBox.sendKeys(Keys.RETURN);
	}

}
