package com.epam.transavia.test;


import com.epam.transavia.page.MainPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.uncommons.reportng.HTMLReporter;

import java.util.concurrent.TimeUnit;

@Listeners({HTMLReporter.class})
public class BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    private WebDriver driver;

    @BeforeClass
    public WebDriver startBrowser() {
        LOG.info("start 'startBrowser'");
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        LOG.info("finish 'startBrowser'");
        return driver;
    }

    protected MainPage navigate(String url) {
        LOG.info("start 'navigate;");
        driver.get(url);
        LOG.info("finish 'navigate'");
        return new MainPage(driver);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}