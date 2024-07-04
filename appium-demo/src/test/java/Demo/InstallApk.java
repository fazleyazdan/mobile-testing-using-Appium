package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;



public class InstallApk {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		// Create uiautomator2 object and set the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pas the emulator name here
		options.setApp("D:\\Apps\\apk files\\UI Demo App.apk");		// path where the apk is located
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address
		
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
		Thread.sleep(7000);
		driver.quit();
	}

}