package com.epam.transavia.test;


import com.epam.transavia.page.MainPage;
import com.epam.transavia.page.ServicePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SixIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(SixIdTest.class);
    private static final String AUTHOR = "Transavia";
    private static final String LINK = "https://www.youtube.com/watch?v=fQMuhniqWAg";

    @Test
    public void runSixIdTest() {
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        mainPage.clickIunderstandBtn();
        mainPage.clickServiceAndThenHandLuggage();
        ServicePage servicePage = new ServicePage(getDriver());
        String link = servicePage.getvideoLinkOnsite();
        Assert.assertEquals(LINK, link);
        String name = servicePage.getvideoNameOnsite();
        servicePage.checkVideoNameAndAuthorOnTheVideoPage(name, AUTHOR);
    }
}
