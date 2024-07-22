package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class LongPress {

	static AndroidDriver driver ;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					// we pass the emulator name here
		options.setAppPackage("com.google.android.dialer");
		options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");
		// create object for AndroidDriver/IOSDriver		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);	// appium server address	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");
		
		// Operations:
		// We will open the dial pad
		// Type the mobile number 
		// And then dial
		// Long press the remove icon to clear the number field
		
		driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();
		
		// Dial This number 0343
		
		driver.findElement(AppiumBy.accessibilityId("0")).click();
		driver.findElement(AppiumBy.accessibilityId("3,DEF")).click();
		driver.findElement(AppiumBy.accessibilityId("4,GHI")).click();
		driver.findElement(AppiumBy.accessibilityId("3,DEF")).click();
		
		WebElement backspace = driver.findElement(AppiumBy.accessibilityId("backspace"));
		backspace.click();   // remove single number
		
		// remove all the number by calling the Function longpress and pass the element on which we are performing the longpress
		longpress(backspace);
		
		// validate if the number field is empty after the longpress
		String number = driver.findElement(By.id("com.google.android.dialer:id/digits")).getText();
		if (number.isBlank())
			System.out.println("Test Case Passed!");
		else
			System.out.println("Test Case Failed");
		
		Thread.sleep(2000);
		driver.quit();

	}

	static void longpress(WebElement el) throws InterruptedException
	{
		
		// find location of web element
		Point location = el.getLocation();
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");  
        // Here in argument we specified which kind of action it is. i.e. Mouse, Touch, Pen etc.
        
        // The Sequence object in Selenium WebDriver and Appium is used to define a series of input actions that can be performed in a sequence.
        // This allows you to create complex user interactions that involve multiple steps, such as dragging and dropping, multi-touch gestures etc.
        Sequence sequence = new Sequence(finger, 1)      // 1 is the id of the sequence actions, you can give any id of Your choice
        		// Moves the pointer (finger) to the center of the element, in our case it is 'backspace' button
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y))
                // Simulates pressing down on the element, it effectively simulates a touch press.
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // This action keeps the pointer in the same position for 1000 milliseconds, simulating the duration of the long press.
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), location.x, location.y))
                // Simulates lifting the finger off the screen
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Arrays.asList(sequence));
	
	}
}
