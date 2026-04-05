package com.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Log.LoggerControl;

public class BrowserWorld {

    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public WebDriver launchBrowser(String webPage, String browser) {

        WebDriver currentDriver = null;

        if ("chrome".equalsIgnoreCase(browser)) {
            currentDriver = new ChromeDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            currentDriver = new EdgeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            currentDriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            System.out.println("Safari browser is not supported in this implementation.");
        } else {
            System.out.println("Invalid browser specified. Please choose 'chrome', 'edge', 'firefox', or 'safari'.");
        }

        if (currentDriver != null) {
            LoggerControl.message("Launched " + browser + " browser successfully. Navigating to: " + webPage).pass();

            drivers.set(currentDriver);

            currentDriver.manage().window().maximize();
            currentDriver.get(webPage);

            LoggerControl.message("Navigated to " + webPage + " successfully.").pass();
        }

        return driver();
    }

    public static WebDriver driver() {
        return drivers.get();
    }

    public void quitDriver() {
        if (driver() != null) {
            driver().quit();
            drivers.remove();
        }
    }
}
