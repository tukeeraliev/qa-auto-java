package com.example.app.base;

import com.example.app.config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;

public class BaseUiTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Если запуск в CI (Jenkins / GitHub Actions)
        if ("true".equalsIgnoreCase(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().deleteAllCookies();

        // ✅ БЕЗОПАСНО получаем URL
        String url = AppConfig.uiBaseUrl();

        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                    "UI base url is not set.\n" +
                            "Provide one of the following:\n" +
                            "1) -Dui.base.url=https://...\n" +
                            "2) environment variable UI_BASE_URL\n" +
                            "3) value in properties file"
            );
        }

        System.out.println("UI BASE URL = " + url);

        driver.get(url);

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear(); window.sessionStorage.clear();"
        );
    }

    private void attachScreenshot() {
        if (driver == null) return;
        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot",
                    new ByteArrayInputStream(bytes));
        } catch (Exception ignored) {}
    }

    private void attachPageSource() {
        if (driver == null) return;
        try {
            byte[] bytes = driver.getPageSource().getBytes(StandardCharsets.UTF_8);
            Allure.addAttachment("Page source", "text/html",
                    new ByteArrayInputStream(bytes), ".html");
        } catch (Exception ignored) {}
    }

    private void attachUrl() {
        if (driver == null) return;
        try {
            Allure.addAttachment("URL", driver.getCurrentUrl());
        } catch (Exception ignored) {}
    }

    private void attachBrowserLogs() {
        if (driver == null) return;
        try {
            LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
            String text = logs.getAll().stream()
                    .map(LogEntry::toString)
                    .collect(Collectors.joining("\n"));

            Allure.addAttachment("Browser console", "text/plain",
                    new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)),
                    ".log");
        } catch (Exception ignored) {}
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result != null && result.getStatus() == ITestResult.FAILURE) {
            attachUrl();
            attachScreenshot();
            attachPageSource();
            attachBrowserLogs();
        }

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {}
            driver = null;
        }
    }
}