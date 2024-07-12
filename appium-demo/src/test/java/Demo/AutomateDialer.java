package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateDialer {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		// First Open the application
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
			options.setAppPackage("com.google.android.dialer");
			options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");
			// create object for AndroidDriver/IOSDriver		
			AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
			System.out.println("application started!");
			
			// Operations :
			// We will open the dial pad
			// Type the mobile number 
			// And then dial
			
			driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();
			
			// Find elements by accessibility id
			
			driver.findElement(AppiumBy.accessibilityId("0")).click();
			driver.findElement(AppiumBy.accessibilityId("3,DEF")).click();
			driver.findElement(AppiumBy.accessibilityId("4,GHI")).click();
			driver.findElement(AppiumBy.accessibilityId("3,DEF")).click();
			driver.findElement(AppiumBy.accessibilityId("dial")).click();         // dial number
			
			// after dialing the number check which number is dialed
			
			String number = driver.findElement(By.id("com.google.android.dialer:id/contactgrid_contact_name")).getText();
			System.out.println("Dialed Phone Number :" + number);
			
			driver.findElement(AppiumBy.accessibilityId("End call")).click();     // end call
			
			Thread.sleep(3000);
			driver.quit();
	}

}
