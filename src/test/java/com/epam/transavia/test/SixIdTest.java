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

    @Test(priority = 6)
    public void runSixIdTest() {
        LOG.info("Start runSixIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        DestinationsPage destinationsPage = mainPage.clickDestinationLink();
        AdvancedSearchPage advancedSearchPage = destinationsPage.clickPerfectDestinationLink();
        advancedSearchPage.fillFromField(FROM);
        advancedSearchPage.clickWhatIsYourBudgetPerPersonLink();
        advancedSearchPage.fillMyBudgetField(MYBUDGET);
        advancedSearchPage.clickSearchButton();
        //Assert.assertTrue(advancedSearchPage.checkIfDestinationsAvailable());
        Assert.assertFalse(advancedSearchPage.checkIfDestinationsAvailable());
        getDriver().close();
    }
}
