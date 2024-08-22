import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginToDashboard()  {
        loginToDashboard();
        // Add assertions here to verify successful login
    }

    @Test(dependsOnMethods = "testLoginToDashboard")
    public void testNavigateToProjectForm() {
        navigateToProjectForm();
        // Add assertions here to verify navigation
    }

    @Test(dependsOnMethods = "testNavigateToProjectForm")
    public void testFillProjectForm() {
        fillProjectForm();
        // Add assertions here to verify form submission
    }

    private void loginToDashboard()  {
        driver.get("https://dashboard.aqaryint.com");
        System.out.println("WELCOME");

        clickElement(By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a"));
        System.out.println("CLICKED VIEW DASHBOARD");

        switchToNewTab();
        System.out.println("SWITCHED TO NEW TAB");

        enterText(By.name("email"), "mark@admin.com");
        enterText(By.name("password"), "mark");

        clickElement(By.xpath("//*[@id=\":r0:\"]"));
        System.out.println("LOGGED IN");
    }

    private void navigateToProjectForm() {
        clickElement(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button"));
        System.out.println("CLICKED PROJECTS");

        clickElement(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/div[2]/div/div/a/div"));
        clickElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[3]/div[1]/a/button"));
    }

    private void fillProjectForm() {
        System.out.println("Filling Project Form");
        setInputValue("projectTitle", "Automation");
        setInputValue("licenseNumber", "mark/123213123/1231");
        setInputValue("projectNumber", "mark/555/1231");

        selectDropdownOption("masterDeveloperSelector", "//*[@id=\"masterDeveloperSelector-option-1\"]");

        scrollPage(1100);

        selectDropdownOption("locationCountrySelect", "//*[@id=\"locationCountrySelect-option-0\"]");
        selectDropdownOption("locationState", "//*[@id=\"locationState-option-0\"]");
        selectDropdownOption("locationCitySelector", "//*[@id=\"locationCitySelector-option-0\"]");
        selectDropdownOption("locationCommunity", "//*[@id=\"locationCommunity-option-0\"]");
        selectDropdownOption("locationSubCommunity", "//*[@id=\"locationSubCommunity-option-0\"]");
        selectDropdownOption("completionStatus", "//*[@id=\"completionStatus-option-1\"]");

        setInputValue("plotArea", "4000");
        setInputValue("builtupArea", "3000");

        selectDropdownOption("furnished", "//*[@id=\"furnished-option-0\"]");
        setInputValue("noOfProperties", "10");
        selectDropdownOption("lifeStyle", "//*[@id=\"lifeStyle-option-0\"]");
        selectDropdownOption("ownership", "//*[@id=\"ownership-option-0\"]");

        selectDate("startDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[2]/button[1]");
        selectDate("completionDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[3]/button[4]");
        selectDate("handoverDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[3]/button[6]");

        setInputValue("serviceCharge", "10");
        scrollPage(500);

        enterText(By.xpath("//*[@id=\"description\"]"), "RealEstate");

        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[5]/div/div[2]/div/div[1]/div[2]/div[1]/div")).click();
        clickElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[6]/div/div[2]/div/div[1]/div[2]/div[1]/div"));
        clickElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[7]/div[2]/div/div[2]/button"));
        scrollPage(1000);
        System.out.println("PROJECT FORM ENTERED > WILL CLOSE IN 5SEC");
        scrollPage(1100);
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    private void setInputValue(String name, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        element.sendKeys(value);
    }

    private void selectDropdownOption(String name, String optionXPath) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
        option.click();
    }

    private void selectDate(String name, String dateXPath) {
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        dateElement.click();
        WebElement dateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));
        dateOption.click();
        WebElement okButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/button[2]"));
        okButton.click();
    }

    private void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private void scrollPage(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")", "");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}