package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ScrollingThroughSwipe {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");

		// Find the center of the screen 
		// Find the size where we wanna scroll upto
		// create sequence of actions
		// perform Scrolling
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		// Get Screen Size
		Dimension size = driver.manage().window().getSize();
		
		// Find the position where you wanna start scrolling/swiping from
		int start_x = size.getWidth()/2;
		int start_y = size.getHeight()/2;
		
		// Find the position where you wanna swipe/scoll upto, from the starting point.
		int end_x = start_x;
		int end_y = start_y/2;
//		int end_y = (int) (size.getHeight() * 0.25);
		
		
		// PointerInput: Represents an input device (e.g., touch, mouse, pen).
        // In our code, PointerInput is created to represent a touch input device.
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");  
        // Here in argument we specified which kind of action it is. i.e. Mouse, Touch, Pen etc.
        
        // The Sequence object in Selenium WebDriver and Appium is used to define a series of input actions that can be performed in a sequence.
        // This allows you to create complex user interactions that involve multiple steps, such as dragging and dropping, multi-touch gestures etc.
        Sequence sequence = new Sequence(finger, 1)      // 1 is the id of the touch, you can give any id of Your choice
        		// Moves the pointer (finger) to the center of the screen
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, start_y))
                // Simulates pressing down on the center of the screen.
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // Adds a pause for 200 milliseconds to simulate the finger holding down on the screen.
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                // Moves the pointer to the end of the y-axis or ending point of scrolling over a duration of 200 milliseconds.
                .addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), end_x, end_y))
                // Simulates lifting the finger off the screen
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // The perform method expects a collection of sequences (hence Arrays.asList(sequence) is used to create a list with the single sequence).
        driver.perform(Arrays.asList(sequence));
		
		
		Thread.sleep(2000);
		driver.quit();
		
	}

}
