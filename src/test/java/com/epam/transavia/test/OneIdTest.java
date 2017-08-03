package com.epam.transavia.test;


import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


public class OneIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(OneIdTest.class);

    @Test
    public void runOneIdTest(){
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened("Where do you want to go?");
        mainPage.clickIunderstandBtn();
//        mainPage.fillFromField("Dublin, Ireland");
//        mainPage.fillToField("Antalya, Turkey");
        mainPage.fillDepartOnDateField("6 Aug 2017");
    }

}
