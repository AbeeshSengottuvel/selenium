package com.screenShot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils; // Make sure you have the commons-io dependency
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotControl {

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {

            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String runTimestamp = new SimpleDateFormat("yyyy-MM-dd_HH").format(new Date());
            String destination = System.getProperty("user.dir") + "/Screenshots/Run_" + runTimestamp + "/" + screenshotName + ".png";
            File finalDestination = new File(destination);

            FileUtils.copyFile(source, finalDestination);

            System.out.println("Screenshot saved at: " + destination);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
