package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.testng.Assert.assertEquals;

/**
 * Home page related step Definitions
 */
public class HomePageSteps extends BaseSteps {
    HomePage viktaHomePage = pageObject(HomePage.class);

    public HomePageSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openHomePage() {
        getDriver().get(getData().baseUrl());
    }

    @Step
    public void searchImageByText(String text) {
        viktaHomePage.searchImage(text);
    }

    @Step
    public void chooseCity() {
        viktaHomePage.chooseCityCategory();
    }

    @Step
    public void chooseMyths() {
        viktaHomePage.chooseMythsCategory();
    }

    @Step
    public void chooseStreet() {
        viktaHomePage.chooseStreetCategory();
    }

    @Step
    public void chooseTech() {
        viktaHomePage.chooseTechCategory();
    }

    @Step
    public void verifySelectedCategory(String expectedCategory) {
        assertThat(viktaHomePage.getTextSelectedCategory()).contains(expectedCategory);
    }

    @Step
    public void verifySearchInputRequest(String text) {
        List<String> titles = viktaHomePage.getProductTitles();
        for (String title : titles) {
            assertThat(title.contains(text));
        }
    }

    @Step
    public void verifyCategoryImgWorks() {
        List<String> imageURLs = viktaHomePage.getCategoryImages();
        for (String str : imageURLs) {
            try {

                URL url = new URL(str);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                assertEquals(responseCode, 200, url + " should return responseCode 200, otherwise image is broken");

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Step
    public void verifySearchBarIsEmptyAfterReset() {
        viktaHomePage.resetSearch();
    }

    @Step
    public void typeSearchQuery(String query) {
        viktaHomePage.typeInSearchQuery(query);
    }

    @Step
    public void verifySearchInputEmpty() {
        assertThat(viktaHomePage.getSearchInput(), isEmptyString());

    }
}