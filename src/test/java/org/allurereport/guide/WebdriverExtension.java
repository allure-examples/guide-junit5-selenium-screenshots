package org.allurereport.guide;

import java.util.Objects;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

    static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(WebdriverExtension.class);
    public static final String WEBDRIVER = "webdriver";

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext,
            final ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(WebDriver.class);
    }

    @Override
    public WebDriver resolveParameter(final ParameterContext parameterContext,
            final ExtensionContext extensionContext) throws ParameterResolutionException {
        final ExtensionContext.Store store = extensionContext.getStore(NAMESPACE);
        return store.get(WEBDRIVER, WebDriver.class);
    }

    @Override
    public void beforeEach(final ExtensionContext context) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);
        store.put(WEBDRIVER, new ChromeDriver());
    }

    @Override
    public void afterEach(final ExtensionContext context) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);
        final WebDriver maybeDriver = store.get(WEBDRIVER, WebDriver.class);
        if (Objects.nonNull(maybeDriver)) {
            maybeDriver.quit();
        }
    }

    public WebDriver getWebDriver(ExtensionContext context) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);

        return store.get(WEBDRIVER, WebDriver.class);
    }
}
