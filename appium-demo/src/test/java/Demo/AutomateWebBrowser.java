package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateWebBrowser {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        // Set up UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options()
            .setDeviceName("Pixel 3a API 34") // Change to your emulator/device name
            .withBrowserName("Chrome")
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")
            .setChromedriverExecutable("C:\\Drivers\\appium_driver\\chromedriver.exe");

        // Initialize the AndroidDriver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

        // Add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Open Chrome browser and navigate to a website
        driver.get("https://www.google.com");
        
        // get title of the web page
        System.out.println("Title of the page is: " + driver.getTitle());
        
        // search  
        WebElement searchBox = driver.findElement(By.name("q"));
        
        // after typing in search box press enter
        // Keys.RETURN used for press Enter. Remeber it must be used with the send keys method
        searchBox.sendKeys("Khalid bin waleed" + Keys.RETURN);     
        // or searchBox.sendKeys(Keys.RETURN);
        

        // Add a delay to see the browser
        Thread.sleep(3000);

        // Quit the driver
        driver.close();
    }
}
