package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.RegistrationSteps;
import lombok.val;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Feature: User registration
 *   As a guest user
 *   I should be able to register new user account(sign-up) and use it to login into the application
 */
public class RegistrationTest extends BaseTest {

  /**
   * Scenario: Regular user is able to login
   */
  @Test(groups = { "smoke", "signup" })
  public void testRegularUserIsAbleToLogin() {
    // Given user opens Registration page
    registrationSteps.openRegistrationPage();

    // When user types in some random values.
    val loginName = registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.LOGINNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.SURNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.FIRSTNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PATRONIM);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.EMAIL);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PASSWORD);

    registrationSteps.verifyCurrentPageIsRegistration();

    registrationSteps.registerNewUser();

    registrationSteps.verifySuccessfulMessage();
    registrationSteps.verifySuccessfulRegistrationMessageIsDisplayed();
    registrationSteps.verifySuccessfulRegistrationMessageContainsNewUsername(loginName);

  }

  @DataProvider(name = "loginNameProvider")
  public Object[][] provideloginNames() {
    Faker faker = new Faker();
    return new Object[][] {
            {faker.lorem().characters(8)},
            {faker.lorem().characters(55)},
            {faker.lorem().characters(1)},
    };
  }

  @Test(enabled = true, dataProvider = "loginNameProvider")
  public void testImpossibleToReUseEmailForRegistration(String login) {
    registrationSteps.openRegistrationPage();
    registrationSteps.registerUserWithGivenLoginName(login);
    registrationSteps.registerUserWithGivenLoginName(login);
    registrationSteps.verifyAndGetLoginMessage();
  }
}

