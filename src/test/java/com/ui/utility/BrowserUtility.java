package com.ui.utility;

import com.ui.constants.Browser;
import com.ui.utility.exceptionUtil.ApplicationErrors;
import com.ui.utility.exceptionUtil.BrowserException;
import com.ui.utility.exceptionUtil.InvalidSelectorFrameworkException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ui.constants.Browser.CHROME;

public class BrowserUtility {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriverWait wait;
    private JavaScriptUtil javaScriptUtil;
    public WebDriver getDriver() {
        javaScriptUtil = new JavaScriptUtil(driver.get());
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver); //Initializa the instace variable driver.
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public BrowserUtility(String browserName) {
        logger.info("Launching Browser for " + browserName);

        switch (browserName.trim().toUpperCase())
        {
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            default:
                logger.error("Invalid Browser Name, Please select Chrome or Firefox or edge");
                throw new BrowserException(ApplicationErrors.BROWSER_NOT_FOUND);
        }
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching Browser for " + browserName);

        switch (browserName){
            case CHROME:
                driver.set(new ChromeDriver());
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            default:
                logger.error("Invalid Browser Name, Please select Chrome or Firefox or edge");
                throw new BrowserException(ApplicationErrors.BROWSER_NOT_FOUND);
        }
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching Browser for " + browserName);

        switch (browserName){
            case CHROME:
                if(isHeadless) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("disabled-gpu");
                    options.addArguments("--window-size=1920,1080");
                    driver.set(new ChromeDriver(options));
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                } else {
                    driver.set(new ChromeDriver());
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                }
                break;
            case FIREFOX:
                if(isHeadless) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless=old");
                    driver.set(new FirefoxDriver());
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                }
                else{
                    driver.set(new FirefoxDriver());
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                }
                break;
            case EDGE:
                if(isHeadless){
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--headless=old");
                    options.addArguments("disabled-gpu");
                    driver.set(new EdgeDriver(options));
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                }
                else{
                    driver.set(new EdgeDriver());
                    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
                }
                break;
            default:
                logger.error("Invalid Browser Name, Please select Chrome or Firefox or EDGE");
                throw new BrowserException(ApplicationErrors.BROWSER_NOT_FOUND);
        }
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }
    public void goToWebsite(String url) {
        logger.info("Visiting the Website - " + url);
        driver.get().get(url);
    }
    public void maximizeWindow() {
        logger.info("Maximizing the Browser window ");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        try {
            logger.info("Finding Element with locator - " + locator);
            WebElement element = waitForelementToBeClickable(locator,30,1);
            logger.info("Element found and now perfoming Click ");
            element.click();
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Locator : " + locator);
        }
    }
    public void clickOnCheckBox(By locator) {
        try {
            logger.info("Finding Element with locator - " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found and now perfoming Click ");
            element.click();
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Locator : " + locator);
        }
    }
    public void clickOn(WebElement element) {
        try{
            logger.info("Element found and now perfoming Click ");
            element.click();
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Element : " + element);
        }
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding Element with locator - " + locator);
        //WebElement element = driver.get().findElement(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter text : " + textToEnter);
        element.sendKeys(textToEnter);
    }
    public void clearText(By textBoxLocator) {
        logger.info("Finding Element with locator - " + textBoxLocator);
        WebElement element = driver.get().findElement(textBoxLocator);
        logger.info("Element found and now clearing textbox field");
        element.clear();
    }
    public void selectFromDropDown(By dropDownLocator, String optionToSelect){
        logger.info("Finding Element with locator - " + dropDownLocator);
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        logger.info("Selecting the option : " + optionToSelect);
        select.selectByVisibleText(optionToSelect);
        //logger.info("Element found and now enter text : " + optionToSelect);
    }
    public String getVisibleText(By locator) {
        logger.info("Finding Element with locator - " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text : " + element.getText());
        return element.getText();
    }
    public String getVisibleText(WebElement element) {
        logger.info("Returning the visible text" + element.getText());
        return element.getText();
    }
    public List<String> getAlltVisibleText(By locator) {
        logger.info("Finding All Elements with locator - " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Elements found and printing the list");
        List<String> visibleTextList = new ArrayList<>();
        for(WebElement element : elementList){
            System.out.println(getVisibleText(element));
            visibleTextList.add(getVisibleText(element));
        }

        return visibleTextList;
    }

    public List<WebElement> getAlltElements(By locator) {
        logger.info("Finding All Elements with locator - " + locator);
        return driver.get().findElements(locator);
    }

    public String takeScreenshot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = System.getProperty("user.dir") + "//screenshot//" + name + "-" + timeStamp +".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void enterSpecialKey(By locator, Keys keysToEnter) {
        logger.info("Finding Element with locator - " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter special key : " + keysToEnter);
        element.sendKeys(keysToEnter);
    }

    // *******************Wait Utilities ***************//
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

     public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(intervalTime))
                .ignoring(NoSuchElementException.class)
                .withMessage("Element is not found");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForelementToBeClickable(By locator, int timeOut, int intervalTime){
        System.out.println("Waiting for element");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(intervalTime))
                .ignoring(ElementNotInteractableException.class)
                .withMessage("Element is NOT interactable");
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}