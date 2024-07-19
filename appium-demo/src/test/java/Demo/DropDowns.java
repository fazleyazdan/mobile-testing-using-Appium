package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DropDowns {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");

		// click View button 
		// click Control
		// click light Theme
		// click drop down
		// click earth
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElements(By.id("android:id/text1")).get(04).click();
		driver.findElements(By.id("android:id/text1")).get(0).click();
		driver.findElement(By.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner1\"]")).click();
		Thread.sleep(1000);
		
		// get option having index 2
		WebElement option = driver.findElements(By.id("android:id/text1")).get(02);
		// save text of that option
		String option_text = option.getText();
		
		// validate if text of that option equal 'Earth'
		if (option_text.equals("Earth"))
		{
			System.out.println("You Selected "+ option_text + " Option");
			option.click();
		}
		else
		{
			System.out.println("You clicked other Option");
			option.click();
		}
		
		Thread.sleep(2000);
		driver.quit();
		
	}

}
