package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EightIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(OneIdTest.class);
    private static final String DESTINATIONFROM ="Dubai, United Arab Emirates";
    private static final String DESTINATIONTO ="Agadir, Morocco";

    @Test(priority = 8)
    public void runEightIdTest(){
        LOG.info("Start runEightIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        mainPage.fillFromField(DESTINATIONFROM);
        mainPage.fillToField(DESTINATIONTO);
        BookAFlightPage bookAFlightPage =  mainPage.clickSearchFlightButton();
        assertTrue(bookAFlightPage.getMessage());
        getDriver().close();
    }
}
