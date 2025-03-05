package org.allurereport.guide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

class ScreenshotTest {

    @RegisterExtension
    WebdriverExtension watcher = new WebdriverExtension();

    @Test
    void lambdaStepTest(WebDriver driver) {
        driver.get("https://allurereport.org/");

        Assertions.assertEquals("Allure Report", driver.getTitle());
    }

}
