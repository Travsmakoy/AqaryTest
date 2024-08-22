import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.ArrayList;

public class TestNG {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void Welcome() {
        // Navigate to Google
        driver.get("https://dashboard.aqaryint.com/");
        // Validation: Check if the current URL is correct
        String currentURL = driver.getCurrentUrl();
        URLValidator.validateURL(driver,"https://dashboard.aqaryint.com/");
        driver.findElement(By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a")).click();

    }
    @Test(priority = 2)
    public void SwitchTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        URLValidator.validateURL(driver,"https://dashboard.aqaryint.com/dashboard/pages/authentication/portal_registration/login");
    }

    @Test(priority = 3)
    public void Login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        email.sendKeys("mark@admin.com");
        WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        pass.sendKeys("mark");
        // Wait for the URL to change to the expected URL
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":r0:\"]")));
        login.click();
        Thread.sleep(100);
        driver.getCurrentUrl();
        // Check if the URL matches the expected URL after login
        URLValidator.validateURL(driver,"https://dashboard.aqaryint.com/dashboard/default");
        if (driver.getCurrentUrl().equals("https://dashboard.aqaryint.com/dashboard/default")) {
            System.out.println("Passed");
        }
        else {
            System.out.println("Failed");
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(100);
//            driver.quit();
        }
    }
    public class URLValidator {
        public static void validateURL(WebDriver driver, String expectedURL) {
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(currentURL, expectedURL, "The URL is incorrect!");
        }

    }
}


