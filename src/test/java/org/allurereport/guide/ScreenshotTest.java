package org.allurereport.guide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class ScreenshotTest {

    @RegisterExtension
    WebdriverExtension webDriverManager = new WebdriverExtension();

    @RegisterExtension
    AutoScreenshotExtension screenshotManager = new AutoScreenshotExtension(
            c -> this.webDriverManager.getWebDriver(c));

    @Test
    void allureCommunityTest(WebDriver webDriver) {
        webDriver.get("https://allurereport.org/");

        final WebElement element = webDriver
                .findElement(By.xpath("//*[contains(text(), 'Trusted by the Community')]"));

        Assertions.assertNotNull(element);

        final WebElement container = element.findElement(By.xpath("./../.."));
        ScreenshotUtils.attachElementScreenshot(container, "Allure Report comminuty.png");
    }

    @Test
    void allureTitleFailedTest(WebDriver webDriver) {
        webDriver.get("https://allurereport.org/");

        Assertions.assertEquals("Allure Report", webDriver.getTitle());
    }
}
