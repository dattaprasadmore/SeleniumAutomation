package com.ui.utility;

import com.ui.tests.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Collections;

public class SeleniumGridUtility extends TestBase {
    private static ThreadLocal<WebDriver> gridDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
    public static WebDriver initGridRemoteDriver(String browser) {

        String GRID_HUB_URL = prop.getProperty("GRID_HUB_URL");

        try{
            switch (browser) {
                case "chrome":
                    gridDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("GRID_HUB_URL")), getChromeOptions()));
                    break;
                case "firefox":
                    gridDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("GRID_HUB_URL")), getFirefoxOptions()));
                    break;
                case "edge":
                    gridDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("GRID_HUB_URL")), getEdgeOptions()));
                    break;
                default:
                    System.out.println("plz pass the right browser on grid..");
                    //throw new BrowserException(AppError.BROWSER_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gridDriver.get();
    }

    private static ChromeOptions getChromeOptions(){
        ChromeOptions co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            co.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            co.setCapability("browserName","chrome");
            //co.setBrowserVersion(prop.getProperty("browserversion").trim());
            co.addArguments("start-maximized");
            co.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }
        return co;
    }
    private static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            fo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            fo.setCapability("browserName","firefox");
            //co.setBrowserVersion(prop.getProperty("browserversion").trim());
            fo.addArguments("start-maximized");
        }
        return fo;
    }
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions eo = new EdgeOptions();

        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            eo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            eo.addArguments("--inPrivate");
        }

        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            eo.setCapability("browserName", "edge");
            // eo.setCapability("enableVNC", true);
        }

        return eo;

    }
}
