package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object of Home page
 */
public class HomePage extends BasePage {
    @FindBy(id = "tbTerm")
    private WebElement tbTerm;

    @FindBy(id = "btnSearch")
    private WebElement btnSearch;

    @FindBy(css = ".product-card__title")
    private List<WebElement> productTitles;

    @FindBy(css = "[id*='category'] img")
    private List<WebElement> categoryElements;

    @FindBy(id = "btnResetSearchCriteria")
    private WebElement btnResetSearchCriteria;

    @FindBy(id = "1")
    private WebElement ctgEmpty;

    @FindBy(id = "2")
    private WebElement ctgFull;

    @FindBy(id = "3")
    private WebElement ctgMyth;

    @FindBy(id = "4")
    private WebElement ctgCity;

    @FindBy(id = "5")
    private WebElement ctgStreet;

    @FindBy(id = "6")
    private WebElement ctgTech;

    @FindBy(id = "7")
    private WebElement ctgWhoUN;

    @FindBy(id = "8")
    private WebElement ctgZinc;

    @FindBy(id = "9")
    private WebElement ctgFantasy;

    @FindBy(id = "10")
    private WebElement ctgLatin;

    @FindBy(id = "11")
    private WebElement ctgDesign;

    @FindBy(id = "12")
    private WebElement ctgKorat;

    @FindBy(id = "13")
    private WebElement ctgEskimo;

    @FindBy(id = "tSelectedCategoryTitle")
    private WebElement selectedCategory;

    public void searchImage(String text) {
        tbTerm.sendKeys(text);
        btnSearch.click();
    }

    public void resetSearch() {
        btnResetSearchCriteria.click();
    }

    public void typeInSearchQuery(String text) {
        tbTerm.sendKeys(text);
    }

    public String getSearchInput() {
        return tbTerm.getAttribute("value");
    }

    public List<String> getProductTitles() {
        List<String> attributeValues = new ArrayList<>();
        for (WebElement title : productTitles) {
            String attributeValue = title.getText();;
            attributeValues.add(attributeValue);
        }

        return attributeValues;
    }

    public List<String> getCategoryImages(){
        List<String> imageLinks = new ArrayList<>();
        for (WebElement category : categoryElements){
            imageLinks.add(category.getAttribute("src"));
        }
        return imageLinks;
    }

    public void chooseMythsCategory() {
        ctgMyth.click();
    }

    public void chooseCityCategory() {
        ctgCity.click();
    }

    public void chooseTechCategory() {
        ctgTech.click();
    }

    public void chooseStreetCategory() {
        ctgStreet.click();
    }

    public String getTextSelectedCategory() {
        return selectedCategory.getText();
    }
}

