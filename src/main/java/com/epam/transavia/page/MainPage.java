package com.epam.transavia.page;

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

    @FindBy(xpath = "html/body/header/nav/div[1]/div[1]/ul/li[4]/a")
    private WebElement serviceLocator;

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
        PageFactory.initElements(driver, this);
    }


    public void clickDestinationLink() {
        destinationsLocator.click();
        LOG.info(" Click on destinations link");
    }

    public void clickManageAndThenViewYourBooking() {
        try {
            manageYourBookingLocator.click();
            Thread.sleep(5000);
            LOG.info(" Manage your booking -click");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", viewYourBookingLocator);
            Thread.sleep(5000);
            //viewYourBookingLocator.click();
            LOG.info(" View your booking -click");
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickServiceAndThenHandLuggage() {
        serviceLocator.click();
        LOG.info(" Service link click");
        handluggageLocator.click();
        LOG.info(" Hand luggage link click");
    }


    public void checkIsMainPageOpened() {
        try {
            String headlineOnsite = linkText.getText();
            LOG.info(String.format("Headline on site = %s", headlineOnsite));
            String checkedHeadline = HEADLINE;
            LOG.info(String.format("Checked headline = %s", checkedHeadline));
            Assert.assertEquals(checkedHeadline, headlineOnsite);
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickIunderstandBtn() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", iunderstendButton);
            LOG.info("I understand Button click");
            Thread.sleep(7000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillFromField(String destinationFrom) {
        try {
            WebElement element = null;
            String s, destinationFromOnsite = "";
            fromField.click();
            Thread.sleep(1000);
            List<WebElement> dropdownsFrom = driver.findElements(By.xpath("//ol[@class='results']/descendant::li"));
            for (int i = 0; i < dropdownsFrom.size(); i++) {
                s = dropdownsFrom.get(i).getText();
                if (destinationFrom.equals(s)) {
                    element = driver.findElement(By.xpath("//ol[@class='results']/li[" + (i + 1) + "]"));
                    destinationFromOnsite = element.getText();
                    LOG.info(String.format("Destination From on site = %s", element.getText()));
                    LOG.info(String.format("Destination From checked = %s", destinationFrom));
                }
            }
            Assert.assertEquals(destinationFrom, destinationFromOnsite);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Thread.sleep(1000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillToField(String destinationTo) {
        try {
            WebElement element = null;
            String s, destinationToOnsite = "";
            toField.click();
            Thread.sleep(1000);


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
                    LOG.info(String.format("Destination To on site = %s", element.getText()));
                    LOG.info(String.format("Destination To checked = %s", destinationTo));
                }
            }
            Assert.assertEquals(destinationTo, destinationToOnsite);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }


    public void fillFromFieldAlternative(String destinationFrom) {
        try {
            fromField.click();
            fromField.sendKeys(destinationFrom);
            departOnDateField.click();
            String text = fromFieldAnswer.getAttribute("innerHTML");
            LOG.info(String.format("Destination From on site = %s", text));
            LOG.info(String.format("Input destination From  = %s", destinationFrom));
            Assert.assertEquals(destinationFrom, text);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillToFieldAlterntive(String destinationTo) {
        try {
            toField.click();
            toField.sendKeys(destinationTo);
            departOnDateField.click();
            String destinationToOnsite = toFieldAnswer.getAttribute("innerHTML");
            LOG.info(String.format("Destination To on site = %s", destinationToOnsite));
            LOG.info(String.format("Input destination To = %s", destinationTo));
            Assert.assertEquals(destinationTo, destinationToOnsite);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillDepartOnDateField(String departDate) {
        try {
            departOnDateField.click();
            departOnDateField.clear();
            departOnDateField.sendKeys(departDate);
            Thread.sleep(1000);
            String departOnDateOnsite = departOnDateField.getAttribute("value");
            LOG.info(String.format("Depart date is = %s", departDate));
            LOG.info(String.format("Depart date on site is = %s", departOnDateOnsite));
            Assert.assertEquals(departDate, departOnDateOnsite);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillReturOnDateField(String returnDate) {
        try {
            returnOnDateField.click();
            returnOnDateField.clear();
            returnOnDateField.sendKeys(returnDate);
            Thread.sleep(1000);
            String returnDateOnsite = returnOnDateField.getAttribute("value");
            LOG.info(String.format("Return date = %s", returnDate));
            LOG.info(String.format("Return date on site = %s", returnDate));
            Assert.assertEquals(returnDate, returnDateOnsite);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void checkReturnOnDateFieldIsEmpty() {
        returnOnDateField.click();
        String returnDateOnsite = returnOnDateField.getAttribute("placeholder");
        LOG.info(String.format("Return date on site = %s", returnDateOnsite));
        Assert.assertEquals(returnDateOnsite, "Single flight");
        LOG.info("Return date on site = Single flight is checked");
    }

    public void uncheckReturnOnCheckbox() {
        try {
            if (returnOnCheckbox.isSelected()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", returnOnCheckbox);
                LOG.info("Checkbox successfully unchecked");
                Thread.sleep(3000);
            } else {
                LOG.info("By now, checkbox was unchecked");
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickSearchFlightButton() {
        try {
            searchFlightButton.click();
            LOG.info("Search flight button click");
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
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
        try {
            addMultipleDestinationsLocator.click();
            LOG.info("Click Add multiple destinations link");
            Thread.sleep(5000);
            return new BookAFlightPage(driver);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
