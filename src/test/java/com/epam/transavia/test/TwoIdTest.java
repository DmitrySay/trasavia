package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.ChooseAFarePage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TwoIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(TwoIdTest.class);
    private static final String DESTINATIONFROM = "Dublin, Ireland";
    private static final String DESTINATIONTO = "Paris (Orly South), France";
    private static final String DEPARTDATE = "20 Oct 2017";
    private static final String RETURNDATE = "25 Oct 2017";


    @Test(priority = 2)
    public void runTwoIdTest() {
        LOG.info("Start runTwoIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        mainPage.fillFromFieldAlternative(DESTINATIONFROM);
        mainPage.fillToFieldAlterntive(DESTINATIONTO);
        mainPage.fillDepartOnDateField(DEPARTDATE);
        mainPage.fillReturOnDateField(RETURNDATE);
        mainPage.addOneAdultandOneChild();
        BookAFlightPage bookAFlightPage = mainPage.clickSearchFlightButton();
        bookAFlightPage.isFlightPresent();
        bookAFlightPage.findTitleInboundFlight();
        bookAFlightPage.findTitleOutboundFlight();
        bookAFlightPage.selectOutboundFlight();
        bookAFlightPage.selectInboundFlight();
        ChooseAFarePage chooseAFarePage = bookAFlightPage.clickBtnNext();
        chooseAFarePage.clickPlusTitle();
        chooseAFarePage.clickBtnSelectInPlusTab();
        chooseAFarePage.checkCorrectCalculations();
        getDriver().close();
    }
}
