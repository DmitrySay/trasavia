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
    private static final String OUTDEPARTDATE = "2 Sep 2017";

    // Inbound flight
    private static final String INDESTINATIONFROM = "Amsterdam (Schiphol), Netherlands";
    private static final String INDESTINATIONTO = "Casablanca, Morocco";
    private static final String INDEPARTDATE = "8 Sep 2017";

    @Test
    public void runNineIdTest() {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        mainPage.clickIunderstandBtn();
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
        bookAFlightPage.clickSelectOutboundFlight();
        bookAFlightPage.clickSelectInboundFlight();
        bookAFlightPage.clickBtnNext();

        ChooseAFarePage chooseAFarePage = new ChooseAFarePage(getDriver());
        double totalPrice =chooseAFarePage.getTotalPrice();
        double totalPriceFromDetails = chooseAFarePage.getDetailPrice();
        assertEquals(totalPrice, totalPriceFromDetails);
        //getDriver().close();
    }
}