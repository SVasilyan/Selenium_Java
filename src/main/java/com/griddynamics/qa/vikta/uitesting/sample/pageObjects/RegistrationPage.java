package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Vikta's Registration page
 */
public class RegistrationPage extends BasePage {

  @FindBy(id = "tbLoginName")
  private WebElement tbLoginName;

  @FindBy(id = "tbSurname")
  private WebElement tbSurname;

  @FindBy(id = "tbFirstName")
  private WebElement tbFirstName;

  @FindBy(id = "tbMiddleName")
  private WebElement tbMiddleName;

  @FindBy(id = "tbEmail")
  private WebElement tbEmail;

  @FindBy(id = "tbPassword")
  private WebElement tbPassword;

  @FindBy(id = "btnSubmitGoToHome")
  private WebElement btnGoToHome;

  @FindBy(id = "tSuccessMessage")
  private WebElement tSuccessMessage;

  @FindBy(id = "lLoginName")
  private WebElement lLoginName;

  @FindBy(id = "btnRegister")
  private WebElement btnRegister;

  public void clickRegisterButton() {
    btnRegister.click();
  }

  public void typeInLoginname(String value) {
    typeIn(value, tbLoginName);
  }

  public void typeInSurname(String value) {
    typeIn(value, tbSurname);
  }

  public void typeInFirstname(String value) {
    typeIn(value, tbFirstName);
  }

  public void typeInPatronim(String value) {
    typeIn(value, tbMiddleName);
  }

  public void typeInEmail(String value) {
    typeIn(value, tbEmail);
  }

  public void typeUsedLoginName(String loginName) {
    typeIn(loginName, tbLoginName);
  }

  public void typeInPassword(String value) {
    typeIn(value, tbPassword);
  }

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public String getUnsuccessfullRegistrationMessageText() {
    return lLoginName.getText();
  }

  public String getSuccessMessageText(){
    return tSuccessMessage.getText();
  }

  public WebElement getMessageWebElement() {
    return tSuccessMessage;
  }
}
