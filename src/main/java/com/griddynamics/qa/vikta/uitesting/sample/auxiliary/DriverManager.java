package com.griddynamics.qa.vikta.uitesting.sample.auxiliary;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataAndProperties;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


@Slf4j
@RequiredArgsConstructor
public final class DriverManager {

  private static final int THOUSAND = 1000;
  private final TestDataAndProperties properties;
  private final ThreadLocal<WebDriver> threadWebDriver = new ThreadLocal<>();

  enum WebDriverType {
    FIREFOX,
    CHROME,
  }

  public void instantiateDriver() {
    log.info("About to init new web driver instance.");
    final WebDriver driver;
    val driverType = getDriverType();

    switch (driverType) {
      case FIREFOX:
        driver = createFirefoxDriver();
        break;
      case CHROME:
        driver = createChromeDriver();
        break;
      default:
        throw new UnsupportedOperationException("Unsupported WebDriver type: " + driverType);
    }

    //driver.manage().window().maximize();

    Configuration.browser = driverType.name().toLowerCase();
    Configuration.startMaximized = true;
    Configuration.timeout = properties.waitTimeout() * THOUSAND;
    Configuration.pageLoadTimeout = properties.pageLoadTimeout() * THOUSAND;

    WebDriverRunner.setWebDriver(driver);
    threadWebDriver.set(driver);
  }

  public void quite() {
    if (Objects.nonNull(threadWebDriver.get())) {
      log.info("Shutting down the driver.");
      threadWebDriver.get().quit();
    }
  }

  public WebDriver get() {
    return threadWebDriver.get();
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) get()).getScreenshotAs(OutputType.BYTES);
  }

  private WebDriverType getDriverType() {
    val driver = properties.browser();
    return Objects.isNull(driver)
      ? WebDriverType.CHROME
      : WebDriverType.valueOf(driver.toUpperCase());
  }

  private FirefoxDriver createFirefoxDriver() {

    final FirefoxOptions ops = new FirefoxOptions();
    ops.addArguments("--width=1022", "--height=722");
    ops.addArguments("--dns-prefetch-disable");
    ops.addArguments("test-type");
    return new FirefoxDriver(ops);
  }

  private ChromeDriver createChromeDriver() {
    final ChromeOptions ops = new ChromeOptions();
    ops.addArguments("--start-maximized");
    ops.addArguments("--dns-prefetch-disable");
    ops.addArguments("test-type");
    return new ChromeDriver(ops);
  }
}
