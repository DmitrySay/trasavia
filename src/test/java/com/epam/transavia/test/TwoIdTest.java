package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.ChooseAFarePage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TwoIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(TwoIdTest.class);
    private static final String HEADLINE = "Where do you want to go?";
    private static final String DESTINATIONFROM = "Dublin, Ireland";
    private static final String DESTINATIONTO = "Paris (Orly South), France";
    private static final String DEPARTDATE = "31 Aug 2017";
    private static final String RETURNDATE = "3 Sep 2017";


    @Test
    public void runTwoIdTest() throws InterruptedException {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened(HEADLINE);
        mainPage.clickIunderstandBtn();
        mainPage.fillFromField(DESTINATIONFROM);
        mainPage.fillToField(DESTINATIONTO);

        mainPage.fillDepartOnDateField(DEPARTDATE);
        mainPage.addOneAdultandOneChild();
        mainPage.clickSearchFlightButton();
        BookAFlightPage bookAFlightPage = new BookAFlightPage(getDriver());
        bookAFlightPage.isFlightPresent();

        bookAFlightPage.findTitleInboundFlight();
        bookAFlightPage.findTitleOutboundFlight();

        bookAFlightPage.selectOutboundFlight();
        bookAFlightPage.selectInboundFlight();

        bookAFlightPage.clickBtnNext();
        ChooseAFarePage chooseAFarePage = new ChooseAFarePage(getDriver());
        chooseAFarePage.clickPlusTitle();
        chooseAFarePage.clickBtnSelectInPlusTab();
        chooseAFarePage.checkCorrectCulculations();

        //getDriver().close();
    }
}
