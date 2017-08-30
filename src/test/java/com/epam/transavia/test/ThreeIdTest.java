package com.epam.transavia.test;

import com.epam.transavia.page.BookingOverviewPage;
import com.epam.transavia.page.LoginPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ThreeIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(ThreeIdTest.class);
    private static final String BOOKINGNUMBER = "MF8C9R";
    private static final String LASTNAME = "kukharau";
    private static final String FLIGHTDATE = "9 June 2016";
    private static final String DESTINATIONFROM = "Pisa";
    private static final String DESTINATIONTO = "Amsterdam (Schiphol)";
    private static final String LOGINTITLE = "Log in";
    private static final String BOOKINGOVERVIEWTITLE = "View your booking";

    @Test(priority = 3)
    public void runThreeIdTest() {
        LOG.info("Start runThreeIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        LoginPage loginPage = mainPage.clickManageAndThenViewYourBooking();
        String expectedTitle = LOGINTITLE;
        LOG.info(String.format("Expected title  = %s", expectedTitle));
        String actualTitle = loginPage.getloginPageTitle();
        LOG.info(String.format("Actual title  = %s", actualTitle));
        assertEquals(expectedTitle, actualTitle);
        BookingOverviewPage bookingOverviewPage = loginPage.doLogin(BOOKINGNUMBER, LASTNAME, FLIGHTDATE);
        assertEquals(BOOKINGOVERVIEWTITLE, bookingOverviewPage.getBookingOverviewPageTitle());
        assertEquals(BOOKINGNUMBER, bookingOverviewPage.getBookingNumber());
        assertEquals(DESTINATIONFROM, bookingOverviewPage.getDestinationFrom());
        assertEquals(DESTINATIONTO, bookingOverviewPage.getDestinationTo());
        getDriver().close();
    }
}