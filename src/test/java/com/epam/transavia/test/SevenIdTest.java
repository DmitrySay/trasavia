package com.epam.transavia.test;

import com.epam.transavia.page.AdvancedSearchPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SevenIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(SevenIdTest.class);
    private static final String DESTINATIONFROM = "Netherlands";
    private static final String DESTINATIONTO = "France";
    private static final String SINGLEFLIGHT = "Single flight";
    private static final String MONTH = "September 2017";
    private static final String RESULT = "Nice, France; 29 евро";

    @Test(priority = 7)
    public void runSevenIdTest() {
        LOG.info("Start runSevenIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        AdvancedSearchPage advancedSearchPage = mainPage.clickPlanAndBookAndThenAdvancedSearch();
        advancedSearchPage.fillFromField(DESTINATIONFROM);
        advancedSearchPage.fillToField(DESTINATIONTO);
        advancedSearchPage.clickWhenWillYouBeTakingOffLink();
        advancedSearchPage.selectTypeOfFlight(SINGLEFLIGHT);
        advancedSearchPage.selectSpecificMonthSingleFlight(MONTH);
        advancedSearchPage.clickSearchButton();
        Assert.assertTrue(advancedSearchPage.checkIfDestinationsAvailable());
        LOG.info(String.format("Check input result = %s", RESULT));
        Assert.assertEquals(RESULT, advancedSearchPage.getCitynameAndPriceFromFirthRow());
        getDriver().close();
    }
}

