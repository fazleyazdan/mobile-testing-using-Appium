package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point; // Use Selenium Point, not java.awt.Point
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DragDropW3C {

    public static void main(String[] args) throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 3a API 34");                    
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");
        
        // Create object for AndroidDriver/IOSDriver        
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));    
        System.out.println("application started!");

        // Click View button
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click drag & drop
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        // Locate source & target element
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement target = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        // Get center of source & target elements
        Point sourceCenter = getCenter(source);
        Point targetCenter = getCenter(target);

        // PointerInput: Represents an input device (e.g., touch, mouse, pen).
        // In our code, PointerInput is created to represent a touch input device.
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");  
        // Here in argument we specified which kind of action it is. i.e. Mouse, Touch, Pen etc.
        
        // The Sequence object in Selenium WebDriver and Appium is used to define a series of input actions that can be performed in a sequence.
        // This allows you to create complex user interactions that involve multiple steps, such as dragging and dropping, multi-touch gestures etc.
        Sequence sequence = new Sequence(finger1, 1)
        		// Moves the pointer (finger) to the center of the source element.
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceCenter.x, sourceCenter.y))
                // Simulates pressing down on the source element.
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // Adds a pause for 588 milliseconds to simulate the finger holding down on the element.
                .addAction(new Pause(finger1, Duration.ofMillis(588)))
                // Moves the pointer to the center of the target element over a duration of 588 milliseconds.
                .addAction(finger1.createPointerMove(Duration.ofMillis(588), PointerInput.Origin.viewport(), targetCenter.x, targetCenter.y))
                // Simulates lifting the finger off the target element, completing the drag and drop action.
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // The perform method expects a collection of sequences (hence Arrays.asList(sequence) is used to create a list with the single sequence).
        driver.perform(Arrays.asList(sequence));
        
        // Validation
        String result_text = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        if (result_text.equals("Dropped!"))
        {
        	System.out.println("Drag & Drop performed Successfully!");
        }
        
        else
        {
        	System.out.println("Drag & Drop Failed...");
        }
    }

    private static Point getCenter(WebElement element) {
        // Get location of the element (it will locate element based on 'x-axis & y-axis')
        Point location = element.getLocation();

        // Get dimensions (height & width of the element)
        Dimension size = element.getSize();

        // Find the center Point (Formula)
        return new Point(location.x + size.width / 2, location.y + size.height / 2);
    }
}
