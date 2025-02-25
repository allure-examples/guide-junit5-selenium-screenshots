package org.allurereport.junit_selenium_screenshots;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScreenshotTest {

    private static ChromeDriver driver;

    @RegisterExtension
    ScreenshotOnFailRule watcher = new ScreenshotOnFailRule(driver, "screenshots");

    @BeforeAll
    public static void setupDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void lambdaStepTest() {
        driver.get("https://en.wikipedia.org/wiki/Software_testing");

        assertFalse(driver.getTitle().contains("Software testing"));
    }

    @AfterAll
    public static void teardownDriver() {
        driver.quit();
    }


}
