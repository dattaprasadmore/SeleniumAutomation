package com.ui.utility;

import com.ui.tests.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility extends TestBase {
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
    public static WebDriver initializeLambdaTestSession(String browser) {
        String LAMBDA_HUB_URL = prop.getProperty("LAMBDA_HUB_URL");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "dattaprasad.more");
        ltOptions.put("accessKey", "wUhWHtuE3snLRUl7oRI2M8EzttvPNO0Z9l79xb4OxpeSupExwm");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        desiredCapabilitiesLocal.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(LAMBDA_HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driverLocal.set(driver);
        return driverLocal.get();
    }

    public static void quitSession(){
        if(driverLocal.get()!=null){
            driverLocal.get().quit();
        }
    }
}