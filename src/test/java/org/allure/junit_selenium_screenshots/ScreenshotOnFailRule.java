package org.allurereport.junit_selenium_screenshots;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ScreenshotOnFailRule implements TestWatcher {

    protected String folder;
    protected WebDriver driver;

    public ScreenshotOnFailRule(WebDriver driver, String folder) {
        this.folder = folder;
        this.driver = driver;
    }

    @Override
    public void testFailed (ExtensionContext context, Throwable throwable) {
        Path path = Paths.get(folder, context.getDisplayName() + ".png");
        Utils.takeScreenshotAllure(driver, path.toString());
    }

}
