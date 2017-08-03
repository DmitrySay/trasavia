package com.epam.transavia.test;

import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by Acer on 01.08.2017.
 */
public class TwoIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(TwoIdTest.class);
    private static final String HEADLINE = "Where do you want to go?";
    private static final String DESTINATIONFROM = "Amsterdam (Schiphol), Netherlands";
    private static final String DESTINATIONTO = "Antalya, Turkey";
    private static final String DEPARTDATE = "20 Aug 2017";


    @Test
    public void runTwoIdTest() throws InterruptedException {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened(HEADLINE);
        mainPage.clickIunderstandBtn();
        //mainPage.addOneAdultandChildren();
        mainPage.addAdultandChildrenToPassengeres(5,7);
        //getDriver().close();
    }
}
