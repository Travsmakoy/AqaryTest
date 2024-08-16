import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class AppTest {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mark\\Downloads\\Seleniu,\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginToDashboard(driver);
        navigateToProjectForm(driver);
        fillProjectForm(driver);

        // Close the driver
        Thread.sleep(2000);
        driver.quit();
    }

    private static void loginToDashboard(WebDriver driver) throws InterruptedException {
        driver.get("https://dashboard.aqaryint.com");
        System.out.println("WELCOME");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickElement(driver, wait, By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a"));
        System.out.println("CLICKED");

        switchToNewTab(driver);
        System.out.println("SWITCHED TO NEW TAB");

        enterText(driver, wait, By.name("email"), "mark@admin.com");
        enterText(driver, wait, By.name("password"), "mark");

        clickElement(driver, wait, By.xpath("//*[@id=\":r0:\"]"));
        System.out.println("SUBMIT");
    }

    private static void navigateToProjectForm(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        clickElement(driver, wait, By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button"));
        System.out.println("CLICKED PROJECTS");

        clickElement(driver, wait, By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/div[2]/div/div/a/div"));
        clickElement(driver, wait, By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[3]/div[1]/a/button"));
    }

    private static void fillProjectForm(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

        setInputValue(driver, shortWait, "projectTitle", "Automation");
        setInputValue(driver, shortWait, "licenseNumber", "mark/123213123/1231");
        setInputValue(driver, shortWait, "projectNumber", "mark/555/1231");

        selectDropdownOption(driver, shortWait, "masterDeveloperSelector", "//*[@id=\"masterDeveloperSelector-option-1\"]");

        scrollPage(driver, 1100);

        selectDropdownOption(driver, shortWait, "locationCountrySelect", "//*[@id=\"locationCountrySelect-option-0\"]");
        selectDropdownOption(driver, shortWait, "locationState", "//*[@id=\"locationState-option-0\"]");
        selectDropdownOption(driver, shortWait, "locationCitySelector", "//*[@id=\"locationCitySelector-option-0\"]");
        selectDropdownOption(driver, shortWait, "locationCommunity", "//*[@id=\"locationCommunity-option-0\"]");
        selectDropdownOption(driver, shortWait, "locationSubCommunity", "//*[@id=\"locationSubCommunity-option-0\"]");
        selectDropdownOption(driver, shortWait, "completionStatus", "//*[@id=\"completionStatus-option-1\"]");

        setInputValue(driver, shortWait, "plotArea", "4000");
        setInputValue(driver, shortWait, "builtupArea", "3000");

        selectDropdownOption(driver, shortWait, "furnished", "//*[@id=\"furnished-option-0\"]");
        setInputValue(driver, shortWait, "noOfProperties", "10");
        selectDropdownOption(driver, shortWait, "lifeStyle", "//*[@id=\"lifeStyle-option-0\"]");
        selectDropdownOption(driver, shortWait, "ownership", "//*[@id=\"ownership-option-0\"]");

        selectDate(driver, wait, "startDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[2]/button[1]");
        selectDate(driver, wait, "completionDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[3]/button[4]");
        selectDate(driver, wait, "handoverDate", "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[3]/button[6]");

        setInputValue(driver, shortWait, "serviceCharge", "10");
        scrollPage(driver, 500);

        enterText(driver, wait, By.xpath("//*[@id=\"description\"]"), "Asdasdasdas");


    }

    private static void clickElement(WebDriver driver, WebDriverWait wait, By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private static void enterText(WebDriver driver, WebDriverWait wait, By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    private static void setInputValue(WebDriver driver, WebDriverWait wait, String name, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        element.sendKeys(value);
    }

    private static void selectDropdownOption(WebDriver driver, WebDriverWait wait, String name, String optionXPath) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
        option.click();
    }

    private static void selectDate(WebDriver driver, WebDriverWait wait, String name, String dateXPath) {
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        dateElement.click();
        WebElement dateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));
        dateOption.click();
        WebElement okButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/button[2]"));
        okButton.click();
    }

    private static void switchToNewTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private static void scrollPage(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")", "");
    }
}
