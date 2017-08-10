package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AdvancedSearchPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(DestinationsPage.class);

    @FindBy(id = "countryStationSelection_Origin-input")
    private WebElement fromFieldLocator;

    @FindBy(xpath = "//form[@id='alternativesearch']/div[3]/div[1]/div[2]/h3")
    private WebElement whatIsYourBudgetPerPersonLocator;

    @FindBy(id = "budgetSelection_EurosBudget")
    private WebElement myBudgetLocator;

    @FindBy(xpath = "//form[@id='alternativesearch']/div[6]/div[2]/button")
    private WebElement searchBtnLocator;

    @FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/descendant::li")
    private List<WebElement> flightLocator;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOG.info("Get Access to Advanced Search Page");
    }

    public void fillFromField(String destinationFrom) {
        try {
            fromFieldLocator.click();
            fromFieldLocator.sendKeys(destinationFrom);
            LOG.info(String.format("Input FROM destination = %s", destinationFrom));
            Thread.sleep(1000);
            String destinationFromOnsite = fromFieldLocator.getAttribute("value");
            LOG.info(String.format("Destination From on site is = %s", destinationFromOnsite));
            Assert.assertEquals(destinationFrom, destinationFromOnsite);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickWhatIsYourBudgetPerPersonLink() {
        try {
            whatIsYourBudgetPerPersonLocator.click();
            LOG.info("Click What Is Your Budget Per Person Link");
            Thread.sleep(2000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillMyBudgetField(String mybudget) {
        try {
            myBudgetLocator.click();
            myBudgetLocator.sendKeys(mybudget);
            LOG.info(String.format("My input budget = %s", mybudget));
            Thread.sleep(2000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickSearchButton() {
        searchBtnLocator.click();
        LOG.info("Click search button");
    }

    public Boolean checkIfDestinationsAvailable() {
        try {
            List<WebElement> destinations = flightLocator;
            LOG.info(String.format("Found %s available destinations", destinations.size()));
            return (destinations.size()!=0) ;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}