package com.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Log.LoggerControl;

public class BrowserWorld {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal();

    public WebDriver launchBrowser(String webPage, String browser) {
        WebDriver CurrentDriver = null;
        if ("chrome".equalsIgnoreCase(browser)) {
            CurrentDriver = new ChromeDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            CurrentDriver = new EdgeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            CurrentDriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            System.out.println("Safari browser is not supported in this implementation.");
        } else {
            System.out.println("Invalid browser specified. Please choose 'chrome', 'edge', 'firefox', or 'safari'.");
        }

        if (CurrentDriver != null) {
            LoggerControl.message("Launched " + browser + " browser successfully." + " Navigating to: " + webPage).pass();

            driver.set(CurrentDriver);
            driver.get().manage().window().maximize();
            driver.get().get(webPage);

            LoggerControl.message("Navigated to " + webPage + " successfully.").pass();
        }
        return driver.get();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
