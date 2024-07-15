package Demo;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartServerViaCode {

	
	public static void main(String[] args) throws MalformedURLException
	
	{
		Map<String, String> env = new HashMap<String, String>(System.getenv());
		env.put("ANDROID_HOME", "C:\\Users\\Fazle Yazdan\\AppData\\Local\\Android\\Sdk");
		env.put("JAVA_HOME", "C:\\Program Files\\Java\\jdk-21");
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Fazle Yazdan\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withEnvironment(env).build();
		
		service.start();
		
		// Create uiautomator2 object and set the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
		options.setApp("D:\\Apps\\apk files\\UI Demo App.apk");		// path where the apk is located
				
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		
		service.stop();
	}
}
