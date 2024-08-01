package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.HomePageSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.LoginSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.RegistrationSteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class BaseTest {

  private DriverManager driverManager;

  LoginSteps loginSteps;
  RegistrationSteps registrationSteps;
  HomePageSteps homePageSteps;

  BaseTest() {
    driverManager = new DriverManager(DataProvider.get());
  }

  @BeforeClass
  void setupClass() {
    driverManager.instantiateDriver();

    loginSteps = new LoginSteps(driverManager.get());
    registrationSteps = new RegistrationSteps(driverManager.get());
    homePageSteps = new HomePageSteps(driverManager.get());
  }


  @AfterMethod
  public void takeScreenshot(ITestResult testname) {
    WebDriver driver = driverManager.get();
    if (ITestResult.FAILURE == testname.getStatus()) {
      try {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshothPath = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(
                screenshothPath,
                new File("./Screenshots/" + testname.getName() + System.currentTimeMillis() + ".png")
        );
        System.out.println("Successfully captured a screenshot");
      } catch (Exception e) {
        System.out.println("Exception while taking screenshot " + e.getMessage());
      }
    }
  }

  @AfterClass
  void tearDownClass() {
    driverManager.quite();
  }
}
