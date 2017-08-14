package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class DestinationsPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(DestinationsPage.class);

    @FindBy(xpath = "//div[@id='top']/div/div[2]/div/div/div/a")
    private WebElement findThePerfectDestinationLocator;


    public DestinationsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Destinations Page");
    }

    public AdvancedSearchPage clickPerfectDestinationLink(){
        findThePerfectDestinationLocator.click();
        LOG.info(" Click on  'Find the perfect destination' link");
        return new AdvancedSearchPage(driver);
     }
}
