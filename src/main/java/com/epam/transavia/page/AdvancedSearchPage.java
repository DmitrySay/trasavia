package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AdvancedSearchPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(DestinationsPage.class);

    @FindBy(id = "countryStationSelection_Origin-input")
    private WebElement fromFieldLocator;

    @FindBy(xpath = "//select[@id='timeFrameSelection_SingleFlight_SpecificMonth']")
    private WebElement specificMonthSingleFlightLocator;

    @FindBy(xpath = "//select[@id='timeFrameSelection_ReturnFlight_SpecificMonth']/../div/span[1]")
    private WebElement checkSpecificMonthLocator;

    @FindBy(id = "countryStationSelection_Destination-input")
    private WebElement toFieldLocator;

    @FindBy(id = "data-flight-type")
    private WebElement selectFlightTypeLocator;

    @FindBy(xpath = "//select[@id='data-flight-type']/../div/span[1]")
    private WebElement checkFlightTypeLocator;

    @FindBy(xpath = "//form[@id='alternativesearch']/div[3]/div[1]/div[2]/h3")
    private WebElement whatIsYourBudgetPerPersonLocator;

    @FindBy(xpath = "//form[@id='alternativesearch']/div[4]/div[1]/div[2]/h3")
    private WebElement whenWillYouBeTakingOffLocator;

    @FindBy(id = "budgetSelection_EurosBudget")
    private WebElement myBudgetLocator;

    @FindBy(xpath = "//form[@id='alternativesearch']/div[6]/div[2]/button")
    private WebElement searchBtnLocator;

    @FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/descendant::li")
    private List<WebElement> flightLocator;

    @FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]/form/div/div/div/div/div/div/h2")
    private WebElement cityNameLocator;

    @FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]/form/div/div/div/div/div/h3")
    private WebElement countryNameLocator;

    @FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]/form/div/div/div/div/div/div[1]/div/div/p/span[2]/span[2]")
    private WebElement priceFromFirthRowLocator;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOG.info("Get Access to Advanced Search Page");
    }

    public void fillFromField(String destinationFrom) {
        fromFieldLocator.click();
        fromFieldLocator.sendKeys(destinationFrom);
        LOG.info(String.format("Input destination FROM = %s", destinationFrom));
        WaitHelper.waitSeconds(1000);
        String destinationFromOnsite = fromFieldLocator.getAttribute("value");
        LOG.info(String.format("Destination From on site is = %s", destinationFromOnsite));
        Assert.assertEquals(destinationFrom, destinationFromOnsite);
    }

    public void fillToField(String destinationTo) {
        toFieldLocator.click();
        toFieldLocator.sendKeys(destinationTo);
        LOG.info(String.format("Input destination TO = %s", destinationTo));
        WaitHelper.waitSeconds(1000);
        String destinationToOnsite = toFieldLocator.getAttribute("value");
        LOG.info(String.format("Destination To on site is = %s", destinationToOnsite));
        Assert.assertEquals(destinationTo, destinationToOnsite);
    }

    public void clickWhatIsYourBudgetPerPersonLink() {
        whatIsYourBudgetPerPersonLocator.click();
        LOG.info("Click 'What Is Your Budget Per Person' Link");
        WaitHelper.waitSeconds(2000);
    }

    public void clickWhenWillYouBeTakingOffLink() {
        whenWillYouBeTakingOffLocator.click();
        WaitHelper.waitSeconds(2000);
        LOG.info("Click 'When will you be taking off' link");
    }

    public void selectTypeOfFlight(String typeOfFlight) {
        Select select = new Select(selectFlightTypeLocator);
        select.selectByVisibleText(typeOfFlight);
        WaitHelper.waitSeconds(2000);
        LOG.info("Click 'When will you be taking off' link");
        String typeFlightLocatorOnSite = checkFlightTypeLocator.getAttribute("innerHTML");
        LOG.info(String.format("Type of Flight Locator On Site = %s", typeFlightLocatorOnSite));
        LOG.info(String.format("Input type of Flight Locator = %s", typeOfFlight));
    }

    public void selectSpecificMonthSingleFlight(String month) {
        Select select = new Select(specificMonthSingleFlightLocator);
        select.selectByVisibleText(month);
        WaitHelper.waitSeconds(2000);
        LOG.info("Click 'Specific month' ");
        String monthOnSite = checkSpecificMonthLocator.getAttribute("innerHTML");
        LOG.info(String.format("Specific month On Site = %s", monthOnSite));
        LOG.info(String.format("Input month = %s", month));
    }

    public void clickSearchButton() {
        searchBtnLocator.click();
        WaitHelper.waitSeconds(5000);
        LOG.info("Click search button");
    }

    public Boolean checkIfDestinationsAvailable() {
        try {
            List<WebElement> destinations = flightLocator;
            LOG.info(String.format("Found %s available destinations", destinations.size()));
            return (destinations.size() != 0);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void fillMyBudgetField(String mybudget) {
        myBudgetLocator.click();
        myBudgetLocator.sendKeys(mybudget);
        LOG.info(String.format("My input budget = %s", mybudget));
        WaitHelper.waitSeconds(2000);
    }

    public String getCitynameAndPriceFromFirthRow() {
        String text = cityNameLocator.getText() + ", " + countryNameLocator.getText() + "; " + priceFromFirthRowLocator.getText() + " евро";
        LOG.info(String.format("Text on site = %s", text));
        return text;
    }
}