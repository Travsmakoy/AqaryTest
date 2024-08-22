//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
//import org.openqa.selenium.remote.service.DriverService;
//
//public class Test {
//    public static void main(String[] args) {
//        // Set the path to the ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//
//        // Build the ChromeDriverService
//        ChromeDriverService service = new ChromeDriverService.Builder()
//                .usingAnyFreePort()
//                .build();
//
//        // Initialize the ChromeDriver
//        WebDriver driver = new ChromeDriver(service);
//
//        // Your test code here
//        driver.get("http://www.google.com");
//
//        // Quit the driver
//        driver.quit();
//    }
//}

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Test {

    static AppiumDriver driver;

    public static void main(String args[]) throws MalformedURLException {

        openMobileApp();
        testApp();

    }
    public static void openMobileApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "OPPO Reno10 5G");
        cap.setCapability("udid ", "7D75SKOBXWOVUSDU");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "14");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("appPackage", "com.aqary.aqary_international");
        cap.setCapability("appActivity", "com.aqary.aqary_international.MainActivity");

        URL url = new URL("http://192.168.1.247:4723");
        driver = new AndroidDriver(url, cap);

        System.out.println("APPLICATION OPEN");
    }

    public static void testApp() throws MalformedURLException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9000));
//        WebDriverWait waitlonger = new WebDriverWait(driver, Duration.ofSeconds(33));// Wait for up to 10 seconds
        WebElement mark = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.view.View[@content-desc='View All'])[1]")));
        mark.click();
        System.out.println("CLICKED HOME SCREEN");
        WebElement mark1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]")));
        mark1.click();
        WebElement rentclick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@content-desc=\"Login\"]")));
        rentclick.click();
        WebElement enterUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")));
        enterUser.click();
        enterUser.sendKeys("mark");
        WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]")));
        pass.click();
        pass.sendKeys("mark");
        System.out.println("CLICKED USER AND PASSWORD");
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Login\"]")));
        login.click();
        WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]")));
        back.click();
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"3341 Properties 1 Sales Unlock the full potential of your property.\"]/android.widget.ImageView[2]")));
        next.click();
        System.out.println("CLICKED NEXT");
        driver.quit();

    }


}
