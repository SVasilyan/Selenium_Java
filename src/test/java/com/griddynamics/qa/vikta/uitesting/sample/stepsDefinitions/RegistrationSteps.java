package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import io.qameta.allure.Step;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * Registration functionality related steps.
 */
public class RegistrationSteps extends BaseSteps {

  RegistrationPage viktaRegistrationPage = pageObject(RegistrationPage.class);

  private static String SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX =
    "User has been registered successfully: ";

  private static String USER_NAME_IS_RESERVED =
          "There is already a user registered with the loginname provided";

  public RegistrationSteps(WebDriver driver) {
    super(driver);
  }

  public enum FieldName {
    LOGINNAME,
    SURNAME,
    FIRSTNAME,
    PATRONIM,
    PASSWORD,
    EMAIL,
  }

  @Step
  public void openRegistrationPage() {
    getDriver().get(getData().registrationPageUrl());
  }

  @Step
  public String typeRandomValueInto(FieldName fieldName) {
    String valueToReturn;
    switch (fieldName) {
      case LOGINNAME:
        valueToReturn = generateRandomUsername();
        viktaRegistrationPage.typeInLoginname(valueToReturn);
        break;
      case SURNAME:
        valueToReturn = generateRandomSurname();
        viktaRegistrationPage.typeInSurname(valueToReturn);
        break;
      case FIRSTNAME:
        valueToReturn = generateRandomFirstName();
        viktaRegistrationPage.typeInFirstname(valueToReturn);
        break;
      case PATRONIM:
        valueToReturn = generateRandomMiddleName();
        viktaRegistrationPage.typeInPatronim(valueToReturn);
        break;
      case EMAIL:
        valueToReturn = generateRandomEmail();
        viktaRegistrationPage.typeInEmail(valueToReturn);
        break;
      case PASSWORD:
        valueToReturn = generateRandomPassword();
        viktaRegistrationPage.typeInPassword(valueToReturn);
        break;

      default:
        throw new IllegalArgumentException(
          "Unsupported Registration page field name: " + fieldName
        );
    }

    return valueToReturn;
  }

  //TODO: Add rest of the steps needed.

  @Step
  public void verifyCurrentPageIsRegistration() {
    assertCurrentPageUrl(
      getData().registrationPageUrl(),
      "Registration page was expected to be the current one."
    );
  }


  @Step
  public void registerNewUser() {
    viktaRegistrationPage.clickRegisterButton();
  }

  @Step
  public void verifySuccessfulMessage() {
    Assert.assertTrue(
            viktaRegistrationPage.getSuccessMessageText().startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX)
    );
  }

  @Step
  public void verifyAndGetLoginMessage() {
    Assert.assertTrue(viktaRegistrationPage.getUnsuccessfullRegistrationMessageText().contains(USER_NAME_IS_RESERVED));
  }


  @Step
  public void verifySuccessfulRegistrationMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(viktaRegistrationPage.getMessageWebElement()));
    assertThat(viktaRegistrationPage.getSuccessMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void verifySuccessfulRegistrationMessageContainsNewUsername(String loginnameUsed) {
    assertThat(viktaRegistrationPage.getSuccessMessageText().trim())
      .as("Successful registration message was expected to contain the new username used.")
      .contains(loginnameUsed);
  }

  @Step
  public void registerUserWithGivenLoginName(String loginName) {
    viktaRegistrationPage.typeInLoginname(loginName);
    typeRandomValueInto(RegistrationSteps.FieldName.SURNAME);
    typeRandomValueInto(RegistrationSteps.FieldName.FIRSTNAME);
    typeRandomValueInto(RegistrationSteps.FieldName.PATRONIM);
    typeRandomValueInto(RegistrationSteps.FieldName.EMAIL);
    typeRandomValueInto(RegistrationSteps.FieldName.PASSWORD);
    registerNewUser();
  }

}
