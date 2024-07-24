package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateWebBrowser {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
		options.withBrowserName("Chrome");
		options.chromedriverUseSystemExecutable();
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");
		driver.get("www.google.com");
		Thread.sleep(2000);
		driver.quit();
	}

}
