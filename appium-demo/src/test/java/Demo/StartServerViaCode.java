package Demo;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartServerViaCode {

	
	public void configureAppium() throws MalformedURLException
	
	{
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Fazle Yazdan\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		
		service.start();
		
		// Create uiautomator2 object and set the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
		options.setApp("D:\\Apps\\apk files\\UI Demo App.apk");		// path where the apk is located
				
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.quit();		
		
		service.stop();
	}
}
