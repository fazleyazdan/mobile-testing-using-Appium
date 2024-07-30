package Demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass {
	
	static AndroidDriver driver;
	
	// we will keep Desired Capabilities Here
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		// Here we created this setup method specifically to automate web browsing
		UiAutomator2Options options = new UiAutomator2Options()
				.setDeviceName("Pixel 3a API 34") // Change to your emulator/device name
				.withBrowserName("Chrome")
				.setPlatformName("Android")
				.setAutomationName("UiAutomator2")
				.setChromedriverExecutable("C:\\Drivers\\appium_driver\\chromedriver.exe");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
	}
	

	@AfterTest
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
		

}
