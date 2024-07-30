package Demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartServerViaCode {

    public static void main(String[] args) throws MalformedURLException {
        
        // Set environment variables
        // Create a map of environment variables from the system environment variables
        Map<String, String> env = new HashMap<>(System.getenv());
        // Add or update specific environment variables
        env.put("ANDROID_HOME", "C:\\Users\\Fazle Yazdan\\AppData\\Local\\Android\\Sdk");
        env.put("JAVA_HOME", "C:\\Program Files\\Java\\jdk-21");

        // Build and start the Appium server
        // Configure the AppiumServiceBuilder with necessary options 
        AppiumDriverLocalService service = new AppiumServiceBuilder()
            .withAppiumJS(new File("C:\\Users\\Fazle Yazdan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .withEnvironment(env)
            .build();

        service.start();
        System.out.println("Appium server started on URL: " + service.getUrl().toString());

        // Create UiAutomator2Options object and set the capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 3a API 34");                    // Emulator name
        options.setApp("D:\\Apps\\apk files\\UI Demo App.apk");      // Path to the APK

        // Create AndroidDriver object
        AndroidDriver driver = new AndroidDriver(new URL(service.getUrl().toString()), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Your test cases execution
        // ...

        // Quit the driver and stop the Appium server
        driver.quit();
        service.stop();
        System.out.println("Appium server stopped.");
    }
}
