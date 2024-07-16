package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SwitchHandling {

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
		// Scroll to Switches
		// Click Switches
		// Turn on Monitor Switch

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		// as we are going to scroll to an element which has text 'Switches'.
		
		
//		 driver.findElementByAndroidUIAutomator(
//				 "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Text of the element\"));");
	
		 
		 WebElement ScrollElement = driver.findElement(AppiumBy.androidUIAutomator(
				    "new UiScrollable(new UiSelector().scrollable(true))" 
				    + ".scrollIntoView(new UiSelector().text(\"Switches\"));")
				);
		 
		 ScrollElement.click();
		 
		 WebElement monitor_switch = driver.findElement(By.id("io.appium.android.apis:id/monitored_switch"));
		 monitor_switch.click();
		 
		 // Validate if the Switch is ON or OFF
		 if (monitor_switch.isSelected() == true)
		 {
			 System.out.println("Monitor Switch is ON");
		 }
		 
		 else
		 {
			 System.out.println("Monitor Switch is OFF");
		 }
		 
		 
		 Thread.sleep(3000);
		 
		 driver.quit();
		
	}

}
