package org.allurereport.guide;

import java.util.Objects;
import java.util.function.Function;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;

public class AutoScreenshotExtension implements TestExecutionExceptionHandler {

    private final Function<ExtensionContext, WebDriver> driverSupplier;

    public AutoScreenshotExtension(Function<ExtensionContext, WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    @Override
    public void handleTestExecutionException(final ExtensionContext context, final Throwable throwable) throws Throwable {
        final WebDriver webDriver = this.driverSupplier.apply(context);
        if (Objects.nonNull(webDriver)) {
            final String attachmentName = "Screenshot on failure (" + throwable.getClass().getSimpleName() + ")";
            ScreenshotUtils.attachPageScreenshot(webDriver, attachmentName);
        }

        throw throwable;
    }
}
