package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataAndProperties;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.BasePage;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class to contain common auxiliary methods for step definitions.
 */
abstract class BaseSteps {

  private WebDriver driver;
  private WebDriverWait wait;

  BaseSteps(WebDriver driver) {
    this.driver = driver;
  }

  WebDriver getDriver() {
    return this.driver;
  }

 WebDriverWait getWait() {
 if (Objects.isNull(this.wait)) {
     this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getData().waitTimeout()));
   }

    return wait;
  }

  TestDataAndProperties getData() {
    return DataProvider.get();
  }

  <P> P getPage(Class<P> pageClass) {
    return PageFactory.initElements(getDriver(), pageClass);
  }

  <P> P pageObject(Class<P> pageObject) {
      return getPage(pageObject);
  }

    public enum UserRole {
        USER,
        ADMIN,
    }

  void verifyCurrentPageIsHomePageForTheUser(String username) {
    BasePage currentPage = getPage(BasePage.class);
    getWait().until(ExpectedConditions.visibilityOf(currentPage.getLoggedInName()));

    assertCurrentPageUrl(getData().baseUrl(), "Home page was expected to be the current one.");

    assertThat(currentPage.getCurrentUserName())
      .as("Unexpected current user's name displayed. Expected: %s", username)
      .contains(username);
  }

    void verifyCurrentUserRole(UserRole userRole) {
        BasePage currentPage = getPage(BasePage.class);
        getWait().until(visibilityOf(currentPage.getLoggedInName()));
        assertThat(currentPage.getCurrentUserRole())
                .as("Unexpected current user's name displayed. Expected: %s", userRole)
                .contains(userRole.toString());
    }

  void assertCurrentPageUrl(String expectedUrl, String messageOnFail) {
    assertThat(getDriver().getCurrentUrl()).as(messageOnFail).contains(expectedUrl);
  }

    protected static String generateRandomString(int maxLength) {
        Faker fakerString = new Faker();
        return fakerString.lorem().characters(maxLength);
    }

    protected static String generateRandomUsername() {
        Faker fakeUsername = new Faker();
        return fakeUsername.name().username();
    }

    protected static String generateRandomEmail() {
        Faker fakeEmail = new Faker();
        return fakeEmail.internet().emailAddress();
    }

    protected static String generateRandomSurname() {
        Faker fakeSurname = new Faker();
        return fakeSurname.name().lastName();
    }

    protected static String generateRandomFirstName() {
        Faker fakeFirstName = new Faker();
        return fakeFirstName.name().firstName();
    }

    protected static String generateRandomMiddleName() {
        Faker fakeMiddleName = new Faker();
        return fakeMiddleName.name().nameWithMiddle();
    }

    protected static String generateRandomPassword() {
        Faker fakePassword = new Faker();
        return fakePassword.internet().password();
    }
}
