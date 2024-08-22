import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;

public class dashboard {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Test
    public void testLogin() {
        driver.get("https://dashboard.aqaryint.com/");
        clickElement(By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a"));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        login("mark@admin.com", "mark");

        // Verify successful login
        WebElement loggedInElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button")));
        assertTrue("Login failed or user menu not found", loggedInElement.isDisplayed());
    }

    @Test
    public void testFormSubmission() {
        // Navigate to the form page
        driver.get("https://dashboard.aqaryint.com/");

        // Click on the link to open a new tab
        clickElement(By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a"));

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Login
        login("mark@admin.com", "mark");

        // Navigate through the menu
        clickElement(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button"));
        clickElement(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/div[8]/div/div/a/div"));
        clickElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[1]/div[3]/div[1]/button"));
        clickElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div[2]/div/div[1]/button"));

        // Verify error message
        WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[1]/div/div[3]"));
        assertTrue("Error message is not displayed!", errorMessage.isDisplayed());
        String expectedMessage = "Please provide a valid promotion type";
        String actualMessage = errorMessage.getText();
        assertEquals("Error message text does not match", expectedMessage, actualMessage);
    }

    private void login(String email, String password) {
        enterText(By.name("email"), email);
        enterText(By.name("password"), password);
        clickElement(By.xpath("//*[@id=\":r0:\"]"));
    }

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(20);
//            driver.quit();
        }
    }
}