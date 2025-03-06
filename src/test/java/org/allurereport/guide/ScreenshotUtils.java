package org.allurereport.guide;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Allure;

public class ScreenshotUtils {

    public static void savePageScreenshot(WebDriver webDriver, Path path) {
        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshotFile.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Can't copy the screenshot file:" + e.getMessage());
        }
    }

    public static void saveElementScreenshot(WebElement webElement, Path path) {
        File screenshotFile = webElement.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshotFile.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Can't copy the screenshot file:" + e.getMessage());
        }
    }

    public static void attachPageScreenshot(WebDriver webDriver, String name) {
        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment(name, new ByteArrayInputStream(screenshotBytes));
    }

    public static void attachElementScreenshot(WebElement webElement, String name) {
        byte[] screenshotBytes = webElement.getScreenshotAs(OutputType.BYTES);
        Allure.attachment(name, new ByteArrayInputStream(screenshotBytes));
    }
}
