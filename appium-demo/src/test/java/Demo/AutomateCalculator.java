package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateCalculator {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		
		// First Open the application
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
		options.setAppPackage("com.google.android.calculator");
		options.setAppActivity("com.android.calculator2.Calculator");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
		
		// Perform the Following Operations:
		// click num 7
		// click +
		// click num 3
		// click =
		// Validate that result equals 10
		
		WebElement num_7 = driver.findElement(By.id("com.google.android.calculator:id/digit_7"));
		num_7.click();
		
		// we can also directly click the element without storing it in a variable
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
		driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
		
		// Validation on result
		
		WebElement result = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		String result_text = result.getText();
		
		if (result_text.equals("101"))
		{
			System.out.println("Test case Passed!");
		}
		else
		{
			System.out.println("Test case Failed");
		}
		
		Thread.sleep(5000);
		driver.quit();      // close the session
	}

}
