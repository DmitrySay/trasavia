package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.ChooseAFarePage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NineIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(OneIdTest.class);
    //Outbound flight
    private static final String OUTDESTINATIONFROM = "Bologna, Italy";
    private static final String OUTDESTINATIONTO = "Eindhoven, Netherlands";
    private static final String OUTDEPARTDATE = "8 Oct 2017";

    // Inbound flight
    private static final String INDESTINATIONFROM = "Amsterdam (Schiphol), Netherlands";
    private static final String INDESTINATIONTO = "Casablanca, Morocco";
    private static final String INDEPARTDATE = "10 Oct 2017";

    @Test(priority = 9)
    public void runNineIdTest() {
        LOG.info("Start runNineIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        BookAFlightPage bookAFlightPage = mainPage.clickAddMultipleDestinationsLink();

        String outdestinationFrom = bookAFlightPage.fillFromOutboundField(OUTDESTINATIONFROM);
        assertEquals(OUTDESTINATIONFROM, outdestinationFrom);
        String outdestinationTo = bookAFlightPage.fillToOutboundField(OUTDESTINATIONTO);
        assertEquals(OUTDESTINATIONTO, outdestinationTo);
        String outDepartDate= bookAFlightPage.fillOutDateField(OUTDEPARTDATE);
        assertEquals(OUTDEPARTDATE, outDepartDate);
        String indestinationFrom = bookAFlightPage.fillFromInboundField(INDESTINATIONFROM);
        assertEquals(INDESTINATIONFROM, indestinationFrom);
        String indestinationTo = bookAFlightPage.fillToInboundField(INDESTINATIONTO);
        assertEquals(INDESTINATIONTO, indestinationTo);
        String inDepartDate= bookAFlightPage.fillInDateField(INDEPARTDATE);
        assertEquals(INDEPARTDATE, inDepartDate);

        bookAFlightPage.clickSearchBtn();
        //bookAFlightPage.clickSelectOutboundFlight();
        //bookAFlightPage.clickSelectInboundFlight();
        bookAFlightPage.selectInboundFlight();
        bookAFlightPage.selectOutboundFlight();

        ChooseAFarePage chooseAFarePage = bookAFlightPage.clickBtnNext();
        double totalPrice =chooseAFarePage.getTotalPrice();
        double totalPriceFromDetails = chooseAFarePage.getDetailPrice();
        assertEquals(totalPrice, totalPriceFromDetails);
        getDriver().close();
    }
}