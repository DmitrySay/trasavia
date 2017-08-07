package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EightIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(OneIdTest.class);
    private static final String HEADLINE ="Where do you want to go?";
    private static final String DESTINATIONFROM ="Dubai, United Arab Emirates";
    private static final String DESTINATIONTO ="Agadir, Morocco";

    @Test
    public void runEightIdTest(){
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened(HEADLINE);
        mainPage.clickIunderstandBtn();
        mainPage.fillFromField(DESTINATIONFROM);
        mainPage.fillToField(DESTINATIONTO);
        mainPage.clickSearchFlightButton();
        BookAFlightPage bookAFlightPage = new BookAFlightPage(getDriver());
        assertTrue(bookAFlightPage.getMessage());
        //getDriver().close();
    }
}
