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

        // CI режим
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

        // ====== ДИАГНОСТИКА ======
        System.out.println("=== CONFIG DEBUG START ===");
        System.out.println("System property ui.base.url = " + System.getProperty("ui.base.url"));
        System.out.println("Env UI_BASE_URL = " + System.getenv("UI_BASE_URL"));
        System.out.println("System property base.url = " + System.getProperty("base.url"));
        System.out.println("Env API_BASE_URL = " + System.getenv("API_BASE_URL"));
        System.out.println("System property profile = " + System.getProperty("profile"));
        System.out.println("===========================");

        String url = AppConfig.uiBaseUrl();

        System.out.println("Resolved UI URL from AppConfig = " + url);

        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                    "UI base url is NOT set.\n" +
                            "Check:\n" +
                            "-Dui.base.url\n" +
                            "UI_BASE_URL env\n" +
                            "config/local.properties or config/ci.properties"
            );
        }

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