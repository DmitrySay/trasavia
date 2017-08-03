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
        LOG.info("Open browser");
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

    protected MainPage navigate(String url) {
        LOG.info(String.format("Open page = %s", url));
        driver.get(url);
        return new MainPage(driver);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
        LOG.info("Close browser");
    }
}