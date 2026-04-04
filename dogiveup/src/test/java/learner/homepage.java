package learner;

import org.testng.annotations.Test;

import com.browser.BrowserWorld;

public class homepage {

    @Test
    public void launchWebPage() throws InterruptedException {
        BrowserWorld browserworld = new BrowserWorld();
        browserworld.launchBrowser("https://www.google.com/", "chrome");
        Thread.sleep(5000);
        browserworld.quitDriver();
    }
}
