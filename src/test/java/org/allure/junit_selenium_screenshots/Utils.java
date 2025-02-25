package org.allurereport.junit_selenium_screenshots;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    @Attachment(type = "image/png")
    public static byte[] takeScreenshotAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void takeScreenshotAllure(WebDriver driver, String name) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try (InputStream is = new ByteArrayInputStream(screenshot)) {
            Allure.attachment(name, is);
        } catch (IOException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }

    public static void takeScreenshot(WebDriver driver, String pathname) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(pathname));
        } catch (IOException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }

}
