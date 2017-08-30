package com.epam.transavia.test;


import com.epam.transavia.page.MainPage;
import com.epam.transavia.page.ServicePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FiveIdTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(FiveIdTest.class);
    private static final String AUTHOR = "Transavia";
    private static final String LINK = "https://www.youtube.com/watch?v=fQMuhniqWAg";

    @Test(priority = 5)
    public void runFiveIdTest() {
        LOG.info("Start runFiveIdTest");
        MainPage mainPage = navigate(MainPage.URL);
        mainPage.checkIsMainPageOpened();
        ServicePage servicePage = mainPage.clickServiceAndThenHandLuggage();
        String link = servicePage.getvideoLinkOnsite();
        Assert.assertEquals(LINK, link);
        String name = servicePage.getvideoNameOnsite();
        servicePage.checkVideoNameAndAuthorOnTheVideoPage(name, AUTHOR);
        getDriver().close();
    }
}
