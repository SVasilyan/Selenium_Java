package com.griddynamics.qa.vikta.uitesting.sample.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class HomePageTest extends BaseTest {

    @Test
    public void testMythCategoryOpensCorrectly() {
        homePageSteps.openHomePage();
        homePageSteps.chooseMyths();
        homePageSteps.verifySelectedCategory("Myths");
    }

    @Test
    public void testCityCategoryOpensCorrectly() {
        homePageSteps.openHomePage();
        homePageSteps.chooseCity();
        homePageSteps.verifySelectedCategory("City");
    }

    @Test
    public void testStreetCategoryOpensCorrectly() {
        homePageSteps.openHomePage();
        homePageSteps.chooseStreet();
        homePageSteps.verifySelectedCategory("Street");
    }

    @Test
    public void testTechCategoryOpensCorrectly() {
        homePageSteps.openHomePage();
        homePageSteps.chooseTech();
        homePageSteps.verifySelectedCategory("Tech");
    }

    @DataProvider(name = "stringProvider")
    public Object[][] provideStrings() {
        return new Object[][] {
                {"T-50"},
                {"Features"},
                {"bb"},
                {"salmon"}
        };
    }

    @Test(dataProvider = "stringProvider")
    public void testResetAndSearchImageByText(String text) {
        homePageSteps.openHomePage();
        homePageSteps.verifySearchBarIsEmptyAfterReset();
        homePageSteps.searchImageByText(text);
        homePageSteps.verifySearchInputRequest(text);
    }

    @Test
    public void testCategoryImagesWorkCorrectly() {
        homePageSteps.openHomePage();
        homePageSteps.verifyCategoryImgWorks();
    }
}





