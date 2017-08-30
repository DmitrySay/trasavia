package com.epam.transavia.test;

import com.epam.transavia.page.BookingOverviewPage;
import com.epam.transavia.page.LoginPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FourIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(FourIdTest.class);
    private static final String BOOKINGNUMBER = "MF8C9R";
    private static final String LASTNAME = "kukharau";
    private static final String FLIGHTDATE = "9 June 2016";
    private static final String BOOKINGOVERVIEWTITLE = "View your booking";

    @Test(priority = 4)
    public void runFourIdTest() {
        LOG.info("Start runFourIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        LoginPage loginPage = mainPage.clickManageAndThenViewYourBooking();
        BookingOverviewPage bookingOverviewPage = loginPage.doLogin(BOOKINGNUMBER, LASTNAME, FLIGHTDATE);
        assertEquals(BOOKINGOVERVIEWTITLE, bookingOverviewPage.getBookingOverviewPageTitle());
        bookingOverviewPage.clickBookingDetails();
        assertEquals(bookingOverviewPage.getTotalprice(), bookingOverviewPage.getPaymentAmount());
        getDriver().close();
    }
}