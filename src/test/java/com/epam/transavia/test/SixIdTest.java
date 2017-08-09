package com.epam.transavia.test;

import com.epam.transavia.page.AdvancedSearchPage;
import com.epam.transavia.page.DestinationsPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;

public class SixIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(SixIdTest.class);
    private static final String FROM = "Innsbruck, Austria";
    private static final String MYBUDGET = "200";

    @Test
    public void runSixIdTest() {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        mainPage.clickIunderstandBtn();
        mainPage.clickDestinationLink();
        DestinationsPage destinationsPage = new DestinationsPage(getDriver());
        destinationsPage.clickPerfectDestinationLink();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(getDriver());
        advancedSearchPage.fillFromField(FROM);
        advancedSearchPage.clickWhatIsYourBudgetPerPersonLink();
        advancedSearchPage.fillMyBudgetField(MYBUDGET);
        advancedSearchPage.clickSearchButton();

         advancedSearchPage.checkIfDestinationsAvailable();
        //Assert.assertTrue(advancedSearchPage.checkIfDestinationsAvailable());
        getDriver().close();
    }
}
