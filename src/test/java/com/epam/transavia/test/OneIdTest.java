package com.epam.transavia.test;

import com.epam.transavia.page.BookAFlightPage;
import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


public class OneIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(OneIdTest.class);
    private static final String DESTINATIONFROM ="Amsterdam (Schiphol), Netherlands";
    private static final String DESTINATIONTO ="Antalya, Turkey";
    private static final String DEPARTDATE ="20 Oct 2017";


    @Test(priority = 1)
    public void runOneIdTest(){
        LOG.info("Start runOneIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
//        mainPage.fillFromField(DESTINATIONFROM);
//        mainPage.fillToField(DESTINATIONTO);
        mainPage.fillFromFieldAlternative(DESTINATIONFROM);
        mainPage.fillToFieldAlterntive(DESTINATIONTO);
        mainPage.fillDepartOnDateField(DEPARTDATE);
        mainPage.uncheckReturnOnCheckbox();
        mainPage.checkReturnOnDateFieldIsEmpty();
        mainPage.checkOneAdultInPassengers();
        BookAFlightPage bookAFlightPage =  mainPage.clickSearchFlightButton();
        bookAFlightPage.isFlightPresent();
        getDriver().close();
    }
}
