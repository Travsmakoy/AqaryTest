import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AqaryMobileAppTestLogin {

    protected static AppiumDriver driver;
    protected static WebDriverWait wait;

    @BeforeTest
    public void openMobileApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("ignoreHiddenApiPolicyError", true);
        cap.setCapability("skipUnlock", true);
        cap.setCapability("noReset", true);
        cap.setCapability("deviceName", "OPPO Reno10 5G");
        cap.setCapability("udid", "7D75SKOBXWOVUSDU");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "14");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("appPackage", "com.aqary.aqary_international");
        cap.setCapability("appActivity", "com.aqary.aqary_international.MainActivity");

        URL url = new URL("http://192.168.1.247:4723");
        driver = new AndroidDriver(url, cap);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("APPLICATION OPEN");
    }

    @org.testng.annotations.Test(priority = 1)
    public void clickViewAll() {
        System.out.println("Clicking 'View All' on Home Screen");
        boolean clicked = clickElement(By.xpath("(//android.view.View[@content-desc='View All'])[1]"));
        Assert.assertTrue(clicked, "'View All' element was not clickable");

    }

    @org.testng.annotations.Test(priority = 2)
    public void clickBackButton() {
        System.out.println("Clicking 'Back' button");
        boolean clicked = clickElement(By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]"));
        Assert.assertTrue(clicked, "'Back' element was not clickable");
    }
    @org.testng.annotations.Test(priority = 3)
    public void Login() {
        System.out.println("Clicking 'Login' on Home Screen");
        boolean clicked = clickElement(By.xpath("//android.widget.ImageView[@content-desc=\"Login\"]"));
        Assert.assertTrue(clicked, "'Login' element was not clickable");
    }
    @org.testng.annotations.Test(priority = 4)
    public void verifyInlineError() {
        System.out.println("Clicking 'Username' on Giving Invalid Email");
        boolean clicked = clickElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"));
        Assert.assertTrue(clicked, "'Login' element was not clickable");
        Sendkeys(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")," ");
    }
    @org.testng.annotations.Test(priority = 5)
    public void EnterUser() {
//        System.out.println("Clicking 'Username' on Login");
//        boolean clicked = clickElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"));
//        Assert.assertTrue(clicked, "'Login' element was not clickable");
        driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")).clear();
        Sendkeys(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"),"mark1@admin.com");
    }
    @org.testng.annotations.Test(priority = 6)
    public void Enterpass() {
        System.out.println("Clicking 'Password' on Login");
        boolean clicked = clickElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]"));
        Assert.assertTrue(clicked, "'Login' element was not clickable");
        Sendkeys(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]"),"mark");
    }
    @org.testng.annotations.Test(priority = 7)
    public void ShowPass() {
        System.out.println("Clicking 'ShowPass' on Login");
        boolean clicked = clickElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.view.View"));
        Assert.assertTrue(clicked, "ShowPassword element was not clickable");
    }

    @org.testng.annotations.Test(priority = 8)
    public void InvalidUserError() throws InterruptedException {
        String errorMessageXPath = "//android.view.View[@content-desc=\"Email & Password Provide valid credentials\"]";

        System.out.println("Clicking 'Login' on Login");
        clickElement(By.xpath("//android.view.View[@content-desc=\"Login\"]"));
        Thread.sleep(1000);
        try {
            WebElement errorMessageElement = driver.findElement(By.xpath(errorMessageXPath));
            if (errorMessageElement.isDisplayed()) {
                Assert.fail("Login failed: Error message 'Email & Password Provide valid credentials' appeared.");
            }
        } catch (NoSuchElementException e) {
            // If the error message is not found, login is considered successful
            System.out.println("Login successful: No error message appeared.");
        }
    }
    @org.testng.annotations.Test(priority = 9)
    public void EnterValidUser() {
//        System.out.println("Clicking 'Username' on Login");
       boolean clicked = clickElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"));
//        Assert.assertTrue(clicked, "'Login' element was not clickable");
        driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")).clear();
        Sendkeys(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"),"mark@admin.com");
    }
    @org.testng.annotations.Test(priority = 10)
    public void SuccessScenarioLogin() throws InterruptedException {
        String errorMessageXPath = "//android.view.View[@content-desc=\"Email & Password Provide valid credentials\"]";

        System.out.println("Clicking 'Login' on Login");
        clickElement(By.xpath("//android.view.View[@content-desc=\"Login\"]"));
        Thread.sleep(1000);
        try {
            // Wait for the error message to appear
            // You may need to implement an explicit wait if necessary
            WebElement errorMessageElement = driver.findElement(By.xpath(errorMessageXPath));
            if (errorMessageElement.isDisplayed()) {
                // If the error message is displayed, fail the test
                Assert.fail("Login failed: Error message 'Email & Password Provide valid credentials' appeared.");
            }
        } catch (NoSuchElementException e) {
            // If the error message is not found, login is considered successful
            System.out.println("Login successful: No error message appeared.");
        }
    }

    @org.testng.annotations.Test(priority = 11)
    public void ClickSideMenu() {
        System.out.println("Clicking 'ShowPass' on Login");
        boolean clicked = clickElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.widget.ImageView[1]"));
        Assert.assertTrue(clicked, "ShowPassword element was not clickable");
    }
    @org.testng.annotations.Test(priority = 12)
    public void ClickSettings() {
        System.out.println("Clicking 'ClickSettings' on Login");
        boolean clicked = clickElement(By.xpath("//android.widget.ImageView[@content-desc=\"Settings\"]"));
        Assert.assertTrue(clicked, "ShowPassword element was not clickable");
    }

    @org.testng.annotations.Test(priority = 13)
    public void ClickLogout() {
        System.out.println("Clicking 'ClickLogout' on Login");
        boolean clicked = clickElement(By.xpath("//android.view.View[@content-desc=\"Log Out\"]"));
        Assert.assertTrue(clicked, "ShowPassword element was not clickable");
    }


    @org.testng.annotations.Test(priority = 14)
    public void ClickX() {
        System.out.println("Clicking 'ClickLogout' on Login");
        boolean clicked = clickElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]"));
        Assert.assertTrue(clicked, "ShowPassword element was not clickable");
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("APPLICATION CLOSED");
        }
    }

    private boolean clickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Successfully clicked element: " + locator.toString());
            return true;
        } catch (Exception e) {
            System.err.println("Failed to click element: " + locator.toString());
            return false;
        }
    }

    private boolean Sendkeys(By locator,String value){
        driver.findElement(locator).sendKeys(value);
        return false;
    }
}
