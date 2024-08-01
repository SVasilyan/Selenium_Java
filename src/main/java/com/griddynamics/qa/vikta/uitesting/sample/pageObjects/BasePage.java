package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasePage {

  @FindBy(id = "sploggedInName")
  private WebElement sploggedInName;

  @FindBy(id = "aLogoutTop")
  private WebElement aLogoutTop;

  @FindBy(id = "aHome")
  private WebElement aHome;

  @FindBy(id = "aAdminHut")
  private WebElement aAdminHut;

  @FindBy(id = "aImages")
  private WebElement aImages;

  @FindBy(id = "aAddImage")
  private WebElement aAddImage;

  @FindBy(id = "aAddCategory")
  private WebElement aAddCategory;

  @FindBy(id = "aUsers")
  private WebElement aUsers;

  @FindBy(id = "aAddUser")
  private WebElement aAddUser;

  @FindBy(id = "aUsersStuffMenuHeader")
  private WebElement aUsersStuffMenuHeader;

  @FindBy(id = "spCartTopMsg")
  private WebElement spCartTopMsg;

  @FindBy(id = "aLogout")
  private WebElement aLogoutBottom;

  @FindBy(xpath = "//*[@id=\"navbar\"]/ul[2]/li/span/span[2]")
  private WebElement spanRole;

  public String getCurrentUserName() {
    return sploggedInName.getText();
  }

  public WebElement getLoggedInName() {
    return sploggedInName;
  }

  public String getCurrentUserRole() {
    return spanRole.getText();
  }

  public void clickLogout() {
    aLogoutTop.click();
  }
}
