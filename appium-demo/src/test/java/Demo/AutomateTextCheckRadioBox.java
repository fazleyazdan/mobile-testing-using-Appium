package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateTextCheckRadioBox {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
	

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");
		
		
		// click view 
		// here, we have multiple elements having same resource id.
		// So first, we will get all the elements & then get specific element through index
		// starting from 0, the view element is present at the 11 index.
		
		driver.findElements(By.id("android:id/text1")).get(11).click();
		
		// click control
		driver.findElements(By.id("android:id/text1")).get(04).click();
		
		// select the light theme
		driver.findElements(By.id("android:id/text1")).get(0).click();
		
		// type text in input box
		driver.findElement(By.id("io.appium.android.apis:id/edit")).sendKeys("Testing");
		
		// click checkbox 1 : by accessibility id
		driver.findElement(AppiumBy.accessibilityId("Checkbox 1")).click();
		
		// click radio button 2
		driver.findElement(AppiumBy.accessibilityId("RadioButton 2")).click();
		
		Thread.sleep(3000);
		driver.quit();  
	}

}
