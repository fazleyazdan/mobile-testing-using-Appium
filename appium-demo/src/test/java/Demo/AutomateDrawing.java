package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateDrawing {

	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 3a API 34");					
		options.setAppPackage("com.saucelabs.mydemoapp.rn");
		options.setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");
		// create object for AndroidDriver/IOSDriver		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));	
		System.out.println("application started!");

		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")).click();
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")).click();
		
		WebElement drawing_el = driver.findElement(By.xpath("//android.webkit.WebView"));
		
		// get location of the element
		Point location = drawing_el.getLocation();
		// get size of the element
		Dimension size = drawing_el.getSize();
		
		// Position where you wanna touch your finger to start drawing from
		int start_x = (location.x + size.getWidth()/2);
		int start_y = (int) (location.y + size.getHeight() * 0.25);
		
		// Position where you wanna end your drawing
		int end_x = start_x;
		int end_y = (location.y + size.getHeight());
//		int end_y = (int) (location.y + size.getHeight() * 0.75);
		
		
		// PointerInput: Represents an input device (e.g., touch, mouse, pen).
        // In our case, PointerInput is created to represent a touch input device.
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");  
        // Here in argument we specified which kind of action it is. i.e. Mouse, Touch, Pen etc.
        
        // The Sequence object in Selenium WebDriver and Appium is used to define a series of input actions that can be performed in a sequence.
        // This allows you to create complex user interactions that involve multiple steps, such as dragging and dropping, multi-touch gestures etc.
        Sequence sequence = new Sequence(finger, 1)      // 1 is the id of the touch, you can give any id of Your choice
        		// Moves the pointer (finger) to the center of the screen.
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, start_y))
                // Simulates pressing down on the screen
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // Adds a pause for 200 milliseconds to simulate the finger holding down on the screen.
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                // Moves the pointer to the end of the y-axis or ending point of drawing over a duration of 200 milliseconds.
                .addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), end_x, end_y))
                // Simulates lifting the finger off the screen
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // The perform method expects a collection of sequences (hence Arrays.asList(sequence) is used to create a list with the single sequence).
        driver.perform(Arrays.asList(sequence));

		Thread.sleep(2000);
		driver.quit();
	}
}