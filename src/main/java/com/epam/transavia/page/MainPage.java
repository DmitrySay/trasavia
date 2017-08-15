package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class MainPage extends BasePage {
    public static final String URL = "https://www.transavia.com/en-EU/home/";
    private static final String HEADLINE = "Where do you want to go?";
    private static final Logger LOG = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//form[@id='desktop']/section/div[1]/h1")
    private WebElement linkText;

    @FindBy(id = "routeSelection_DepartureStation-input")
    private WebElement fromField;

    @FindBy(xpath = "//input[@id='routeSelection_DepartureStation-input']/../../../div[2]/span[1]")
    private WebElement fromFieldAnswer;

    @FindBy(xpath = "//div[@class='cc-left']/button")
    private WebElement iunderstendButton;

    @FindBy(id = "routeSelection_ArrivalStation-input")
    private WebElement toField;

    @FindBy(xpath = "//input[@id='routeSelection_ArrivalStation-input']/../../../div[2]/span[1]")
    private WebElement toFieldAnswer;

    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement departOnDateField;

    @FindBy(id = "dateSelection_IsReturnFlight-datepicker")
    private WebElement returnOnDateField;

    @FindBy(id = "dateSelection_IsReturnFlight")
    private WebElement returnOnCheckbox;

    @FindBy(xpath = "//form[@id='desktop']/section/div[3]/div/button")
    private WebElement searchFlightButton;

    @FindBy(xpath = "//input[@id='booking-passengers-input']")
    private WebElement whoWillBeTravellingField;

    @FindBy(xpath = "//input[@id='booking-passengers-input']/../div")
    private WebElement quantityOfPassengers;

    @FindBy(xpath = "//div[@class='selectfield adults']/div/div[2]/div/div/button[1]")
    private WebElement minusAdultlocator;

    @FindBy(xpath = "//div[@class='selectfield adults']/div/div[2]/div/div/div/input")
    private WebElement textAdultlocator;

    @FindBy(xpath = "//div[@class='selectfield adults']/div/div[2]/div/div/button[2]")
    private WebElement plusAdultlocator;

    @FindBy(xpath = "//div[@class='selectfield children']/div/div[2]/div/div/button[1]")
    private WebElement minusChildrenlocator;

    @FindBy(xpath = "//div[@class='selectfield children']/div/div[2]/div/div/div/input")
    private WebElement textChildrenlocator;

    @FindBy(xpath = "//div[@class='selectfield children']/div/div[2]/div/div/button[2]")
    private WebElement plusChildrenlocator;

    @FindBy(xpath = "//div[@class='togglepanel']/div[2]/button")
    private WebElement saveLocator;

    @FindBy(xpath = "html/body/header/nav/div[1]/div[1]/ul/li[3]/a")
    private WebElement manageYourBookingLocator;

    @FindBy(xpath = "html/body/header/nav/div[1]/div[1]/ul/li[2]/a")
    private WebElement planAndBookLocator;

    @FindBy(xpath = "html/body/header/nav/div[1]/div[1]/ul/li[4]/a")
    private WebElement serviceLocator;

    @FindBy(xpath = "//div[@id='horizontal-sub-navigation-planandbook']/div/div[2]/div/div[2]/div[2]/ul/li[6]/a")
    private WebElement advancedSearchLocator;

    @FindBy(xpath = "//div[@id='horizontal-sub-navigation-service']/div/div[2]/div/div[2]/div[2]/ul/li[1]/a")
    private WebElement handluggageLocator;

    @FindBy(xpath = "//div[@id='horizontal-sub-navigation-manageyourbooking']/div/div[2]/div/div[1]/div/ul/li[2]/a")
    private WebElement viewYourBookingLocator;

    @FindBy(xpath = "//form[@id='desktop']/section/div[3]/ul/li[2]/a")
    private WebElement addMultipleDestinationsLocator;

    @FindBy(xpath = "html/body/header/nav/div[1]/div[2]/ul/li[3]/a")
    private WebElement destinationsLocator;


    public MainPage(WebDriver driver) {
        super(driver);
        WaitHelper.waitLogoTtransavia(driver, 15);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Main Page");
        clickIunderstandBtn();
    }

    public AdvancedSearchPage clickPlanAndBookAndThenAdvancedSearch() {
        planAndBookLocator.click();
        LOG.info(" Click on 'plan and book' link");
        WaitHelper.waitSeconds(1000);
        advancedSearchLocator.click();
        LOG.info(" Click on 'advanced search' link");
        WaitHelper.waitSeconds(5000);
        return new AdvancedSearchPage(driver);
    }

    public DestinationsPage clickDestinationLink() {
        destinationsLocator.click();
        LOG.info(" Click on destinations link");
        return new DestinationsPage(driver);
    }

    public LoginPage clickManageAndThenViewYourBooking() {
        manageYourBookingLocator.click();
        LOG.info(" Manage your booking -click");
        WaitHelper.waitIsElementClickable(driver, viewYourBookingLocator);
        viewYourBookingLocator.click();
        LOG.info(" View your booking -click");
        return new LoginPage(driver);
    }

    public ServicePage clickServiceAndThenHandLuggage() {
        serviceLocator.click();
        WaitHelper.waitSeconds(5000);
        LOG.info(" Service link click");
        handluggageLocator.click();
        LOG.info(" Hand luggage link click");
        WaitHelper.waitSeconds(5000);
        return new ServicePage(driver);
    }

    public void checkIsMainPageOpened() {
        String headlineOnsite = linkText.getText();
        LOG.info(String.format("Headline on site = %s", headlineOnsite));
        String checkedHeadline = HEADLINE;
        LOG.info(String.format("Checked headline = %s", checkedHeadline));
        Assert.assertEquals(checkedHeadline, headlineOnsite);
    }

    public void clickIunderstandBtn() {
        WaitHelper.waitSeconds(5000);
        iunderstendButton.click();
        LOG.info("I understand Button click");
        WaitHelper.waitFeedbackLogo(driver, 15);
    }

    public String fillFromField(String destinationFrom) {
        WebElement element = null;
        String s, destinationFromOnsite = "";
        fromField.click();
        WaitHelper.waitSeconds(1000);
        List<WebElement> dropdownsFrom = driver.findElements(By.xpath("//ol[@class='results']/descendant::li"));
        for (int i = 0; i < dropdownsFrom.size(); i++) {
            s = dropdownsFrom.get(i).getText();
            if (destinationFrom.equals(s)) {
                element = driver.findElement(By.xpath("//ol[@class='results']/li[" + (i + 1) + "]"));
                destinationFromOnsite = element.getText();
                LOG.info(String.format("Destination From on site = %s", destinationFromOnsite));
                LOG.info(String.format("Destination From checked = %s", destinationFrom));
            }
        }
        Assert.assertEquals(destinationFrom, destinationFromOnsite);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        WaitHelper.waitSeconds(1000);
        return destinationFromOnsite;
    }

    public String fillToField(String destinationTo) {
        WebElement element = null;
        String s, destinationToOnsite = "";
        toField.click();
        WaitHelper.waitSeconds(1000);
        //Destinations from Deperture airport
        List<WebElement> dropdownsToFirthLi = driver.findElements(By.xpath("//ol[@class='results']/li[1]/ol/descendant::li"));
        //Destinations from other airports:
        List<WebElement> dropdownsToSecondLi = driver.findElements(By.xpath("//ol[@class='results']/li[2]/ol/descendant::li"));

        for (int i = 0; i < dropdownsToFirthLi.size(); i++) {
            s = dropdownsToFirthLi.get(i).getText();
            if (destinationTo.equals(s)) {
                element = driver.findElement(By.xpath("//ol[@class='results']/li[1]/ol/li[" + (i + 1) + "]"));
                destinationToOnsite = element.getText();
                LOG.info(String.format("destinationToOnsite = %s", element.getText()));
                LOG.info(String.format("destinationTo = %s", destinationTo));
            }
        }

        for (int i = 0; i < dropdownsToSecondLi.size(); i++) {
            s = dropdownsToSecondLi.get(i).getText();
            if (destinationTo.equals(s)) {
                element = driver.findElement(By.xpath("//ol[@class='results']/li[2]/ol/li[" + (i + 1) + "]"));
                destinationToOnsite = element.getText();
                LOG.info(String.format("Destination To on site = %s", destinationToOnsite));
                LOG.info(String.format("Destination To checked = %s", destinationTo));
            }
        }
        Assert.assertEquals(destinationTo, destinationToOnsite);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        WaitHelper.waitSeconds(1000);
        return destinationToOnsite;
    }

    public String fillFromFieldAlternative(String destinationFrom) {
        fromField.click();
        fromField.sendKeys(destinationFrom);
        linkText.click();
        linkText.click();
        String destinationFromOnsite = fromFieldAnswer.getAttribute("innerHTML");
        LOG.info(String.format("Destination From on site = %s", destinationFromOnsite));
        LOG.info(String.format("Input destination From  = %s", destinationFrom));
        Assert.assertEquals(destinationFrom, destinationFromOnsite);
        return destinationFromOnsite;
    }

    public String fillToFieldAlterntive(String destinationTo) {
        toField.click();
        toField.sendKeys(destinationTo);
        linkText.click();
        linkText.click();
        String destinationToOnsite = toFieldAnswer.getAttribute("innerHTML");
        LOG.info(String.format("Destination To on site = %s", destinationToOnsite));
        LOG.info(String.format("Input destination To = %s", destinationTo));
        Assert.assertEquals(destinationTo, destinationToOnsite);
        return destinationToOnsite;
    }

    public String fillDepartOnDateField(String departDate) {
        departOnDateField.click();
        departOnDateField.clear();
        departOnDateField.sendKeys(departDate);
        WaitHelper.waitSeconds(1000);
        String departOnDateOnsite = departOnDateField.getAttribute("value");
        LOG.info(String.format("Depart date is = %s", departDate));
        LOG.info(String.format("Depart date on site is = %s", departOnDateOnsite));
        Assert.assertEquals(departDate, departOnDateOnsite);
        return departOnDateOnsite;
    }

    public String fillReturOnDateField(String returnDate) {
        returnOnDateField.click();
        returnOnDateField.clear();
        returnOnDateField.sendKeys(returnDate);
        WaitHelper.waitSeconds(1000);
        String returnDateOnsite = returnOnDateField.getAttribute("value");
        LOG.info(String.format("Return date = %s", returnDate));
        LOG.info(String.format("Return date on site = %s", returnDate));
        Assert.assertEquals(returnDate, returnDateOnsite);
        return returnDateOnsite;
    }

    public Boolean checkReturnOnDateFieldIsEmpty() {
        returnOnDateField.click();
        String returnDateOnsite = returnOnDateField.getAttribute("placeholder");
        LOG.info(String.format("Return date on site = %s", returnDateOnsite));
        Assert.assertEquals(returnDateOnsite, "Single flight");
        LOG.info("Return date on site = Single flight is checked");
        return ("Single flight".equals(returnDateOnsite));
    }

    public void uncheckReturnOnCheckbox() {
        if (returnOnCheckbox.isSelected()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", returnOnCheckbox);
            LOG.info("Checkbox successfully unchecked");
            WaitHelper.waitSeconds(1000);
        } else {
            LOG.info("By now, checkbox was unchecked");
        }
    }

    public BookAFlightPage clickSearchFlightButton() {
        searchFlightButton.click();
        LOG.info("Search flight button click");
        return new BookAFlightPage(driver);
    }

    public void addAdultsAndChildrenToPassengeres(int adult, int children) {
        whoWillBeTravellingField.click();
        textAdultlocator.sendKeys(Keys.BACK_SPACE);
        String adultTostring = String.valueOf(adult);
        textAdultlocator.sendKeys(adultTostring);
        textChildrenlocator.sendKeys(Keys.BACK_SPACE);
        String childrenTostring = String.valueOf(children);
        textChildrenlocator.sendKeys(childrenTostring);
        saveLocator.click();
        LOG.info(String.format("adults = %d", adult));
        LOG.info(String.format("children = %d", children));
        LOG.info("Add adults and Children to passengers completed");
    }

    public void addOneAdultandOneChild() {
        whoWillBeTravellingField.click();
        plusAdultlocator.click();
        plusChildrenlocator.click();
        saveLocator.click();
        LOG.info("Add One extra adult and One Child to passengers completed");
    }

    /*
    METHOD GETS TEXT FROM whoWillBeTravellingField AND CHECK IS TEXT == PASSENGERS
     */
    public void checkOneAdultInPassengers() {
        String passengers = quantityOfPassengers.getAttribute("innerHTML");
        Assert.assertEquals(passengers, "1 Adult");
        LOG.info("1 Adult in passengers is checked");
    }

    public BookAFlightPage clickAddMultipleDestinationsLink() {
        addMultipleDestinationsLocator.click();
        LOG.info("Click Add multiple destinations link");
        return new BookAFlightPage(driver);
    }
}
