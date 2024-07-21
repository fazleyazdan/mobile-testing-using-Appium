package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppManagement {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
	
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		
		
		String packageName = "io.appium.android.apis";
		
		// uninstall/remove the app
		driver.removeApp(packageName);
		
		// check if the application is installed or not
		if (!driver.isAppInstalled(packageName))
		{
			driver.installApp("D:\\Apps\\apk files\\ApiDemos-debug.apk");
			System.out.println("App Successfully Installed");
		}
		
		else
			System.out.println("App is already Installed..");
		
		// Activate the given app if it is not running or running in the background
		driver.activateApp(packageName);
		
		// Terminate the running app
		driver.terminateApp(packageName);
		
		
	}

}
