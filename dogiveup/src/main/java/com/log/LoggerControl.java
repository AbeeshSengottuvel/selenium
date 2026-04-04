package com.Log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.browser.BrowserWorld;
import static com.screenShot.ScreenshotControl.captureScreenshot;

public class LoggerControl {

    public static Logger log = LogManager.getLogger(LoggerControl.class);

    public static logBuilder message(String stepdescription) {
        return new logBuilder(stepdescription);
    }

    public static class logBuilder {

        String stepdescription;

        public logBuilder(String stepdescription) {
            this.stepdescription = stepdescription;
        }

        public void pass() {
            log.info("PASS: " + stepdescription);
            captureScreenshot(BrowserWorld.driver.get(), stepdescription.replaceAll(" ", "_"));
        }

        public void fail() {
            log.error("FAIL: " + stepdescription);
            captureScreenshot(BrowserWorld.driver.get(), stepdescription.replaceAll(" ", "_"));
        }

        public void ignore() {
            log.warn("IGNORE: " + stepdescription);
        }
    }
}
