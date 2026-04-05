package learner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Log.LoggerControl;
import com.browser.BrowserWorld;

public class homepage {

    @Test
    public void launchWebPage() {
        LoggerControl.message("--- Test Started: Automating Chrome New Tab Page ---").pass();
        BrowserWorld browserworld = new BrowserWorld();

        LoggerControl.message("Attempting to launch Chrome and navigate to chrome://newtab/").pass();
        WebDriver driver = browserworld.launchBrowser("chrome://newtab/", "chrome");
        LoggerControl.message("Chrome New Tab page launched successfully.").pass();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            LoggerControl.message("Waiting up to 10 seconds for the first Shadow Host (<ntp-app>)...").pass();
            WebElement host1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ntp-app")));
            LoggerControl.message("First Shadow Host located successfully.").pass();

            SearchContext shadowroot1 = host1.getShadowRoot();
            LoggerControl.message("Pierced the first Shadow Root.").pass();

            LoggerControl.message("Looking for the second Shadow Host (<cr-searchbox>)...").pass();
            WebElement host2 = shadowroot1.findElement(By.cssSelector("cr-searchbox#searchbox"));
            LoggerControl.message("Second Shadow Host located successfully.").pass();

            SearchContext shadowroot2 = host2.getShadowRoot();
            LoggerControl.message("Pierced the second Shadow Root.").pass();

            LoggerControl.message("Looking for the actual input field...").pass();
            WebElement searchbox = shadowroot2.findElement(By.cssSelector("input#input"));
            LoggerControl.message("Input field found! Typing 'youtube'...").pass();
            searchbox.sendKeys("youtube");

            LoggerControl.message("Pressing the ENTER key...").pass();
            Actions actioncontrol = new Actions(driver);
            actioncontrol.sendKeys(Keys.ENTER).perform();

            LoggerControl.message("Search executed successfully!").pass();

        } catch (Exception e) {

            LoggerControl.message("TEST FAILED! Exception caught. Current URL is: " + driver.getCurrentUrl()).pass();
            System.out.println("Error Details: " + e.getMessage());
            throw e;
        } finally {

            LoggerControl.message("Cleaning up and closing the browser.").pass();
            browserworld.quitDriver();
            LoggerControl.message("--- Test Finished ---").pass();
        }
    }
}
