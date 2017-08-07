package com.epam.transavia.test;

import com.epam.transavia.page.BookingOverviewPage;
import com.epam.transavia.page.LoginPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FourIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(TwoIdTest.class);
    private static final String HEADLINE = "Where do you want to go?";
    private static final String BOOKINGNUMBER = "MF8C9R";
    private static final String LASTNAME = "kukharau";
    private static final String FLIGHTDATE = "9 June 2016";
    private static final String DESTINATIONFROM = "Pisa";
    private static final String DESTINATIONTO = "Amsterdam (Schiphol)";
    private static final String LOGINTITLE = "Log in";
    private static final String BOOKINGOVERVIEWTITLE = "View your booking";

    @Test
    public void runFourIdTest() {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened(HEADLINE);
        mainPage.clickIunderstandBtn();
        mainPage.clickManageAndThenViewYourBooking();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin(BOOKINGNUMBER, LASTNAME, FLIGHTDATE);

        BookingOverviewPage bookingOverviewPage = new BookingOverviewPage(getDriver());
        assertEquals(BOOKINGOVERVIEWTITLE, bookingOverviewPage.getBookingOverviewPageTitle());
        bookingOverviewPage.clickBookingDetails();
        assertEquals(bookingOverviewPage.getTotalprice(), bookingOverviewPage.getPaymentAmount());
        //getDriver().close();
    }
}