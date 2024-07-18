package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions; 
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

// in some of the imports we have used 'static', so that we can use methods directly without creating an object for it

public class DragDropTouchActions {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");

		// click View button
		// click drag & drop
		// locate source & target element
		// perform drag & drop
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		
		WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement target = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
		
		// Perform Drag & Drop using TouchActions.
		TouchAction actions = new TouchAction(driver);
		
		LongPressOptions longPressOption = new LongPressOptions();

		longPressOption.withElement(element(source));                    
		actions.longPress(longPressOption).moveTo(element(target)).release().perform();
		
//      The above method for drag & drop is recommended. You can also use the below technique.		
//		actions.longPress(longPressOption.withElement(element(source))).moveTo(element(target)).release().perform();
		
		Thread.sleep(2000);
		driver.quit();
		
	}

}
