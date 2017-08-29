package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;


public class BookAFlightPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BookAFlightPage.class);
    private static final By locator = By.xpath("//span[@class='price']/span[1]");
    private static final By outboundFlightTitleLocator = By.xpath("//h2[contains(text(),'Outbound flight')]/text()");
    private static final By outboundLocator = By.xpath("//div[@class='outbound']//div[@class='is-enabled']");
    private static final By inboundFlightTitleLocator = By.xpath("//h2[contains(text(),'Inbound flight')]/text()");
    private static final By inboundLocator = By.xpath("//div[@class='inbound']//div[@class='is-enabled']");
    private static final String textError = "Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again.";
    //выбор рейса
    private static final By outboundFlightLocator = By.xpath("//section[@class='flight outbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li");
    private static final By inboundFlightLocator = By.xpath("//section[@class='flight inbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li");

    //select вылет
    private static final By outboundFlightSelectBtn = By.xpath("//section[@class='flight outbound']/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[2]/span");
    private static final By inboundFlightSelectBtn = By.xpath("//section[@class='flight inbound']/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[2]/span");

    @FindBy(name = "next_button")
    private WebElement nextBtn;

    @FindBy(xpath = "html/body/header/nav/div[1]/h1")
    private WebElement bookAflightLocator;

    @FindBy(xpath = "//form[@id='flights']/div/section/div[2]/div[1]/div/div/div[1]/div/div/div[2]/p")
    private WebElement errorMessageLocator;

    @FindBy(id = "openJawRouteSelection_DepartureStationOutbound-input")
    private WebElement outFromField;

    @FindBy(xpath = "//input[@id='openJawRouteSelection_DepartureStationOutbound-input']/../../../div[2]/span[1]")
    private WebElement textOutFromField;

    @FindBy(id = "openJawRouteSelection_ArrivalStationOutbound-input")
    private WebElement outToField;

    @FindBy(xpath = "//input[@id='openJawRouteSelection_ArrivalStationOutbound-input']/../../../div[2]/span[1]")
    private WebElement textOutToField;

    @FindBy(id = "openJawRouteSelection_DepartureStationInbound-input")
    private WebElement inFromField;

    @FindBy(xpath = "//input[@id='openJawRouteSelection_DepartureStationInbound-input']/../../../div[2]/span[1]")
    private WebElement textInFromField;

    @FindBy(id = "openJawRouteSelection_ArrivalStationInbound-input")
    private WebElement inToField;

    @FindBy(xpath = "//input[@id='openJawRouteSelection_ArrivalStationInbound-input']/../../../div[2]/span[1]")
    private WebElement textInToField;

    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement outDateField;

    @FindBy(id = "dateSelection_InboundDate-datepicker")
    private WebElement inDateField;

    @FindBy(xpath = "//form[@id='flights']/div/section/div[3]/div/button[2]")
    private WebElement searchButton;

    public BookAFlightPage(WebDriver driver) {
        super(driver);
        WaitHelper.waitFeedbackLogo(driver, 15);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Book A Flight Page");
    }


    public String fillToOutboundField(String outDestinationTo) {
        outToField.click();
        outToField.sendKeys(outDestinationTo);
        bookAflightLocator.click();
        String text = outToField.getAttribute("value");
        LOG.info(String.format("Outbound Destination To on site = %s", text));
        LOG.info(String.format("Outbound Destination To checked = %s", outDestinationTo));
        return text;
    }

    public String fillFromOutboundField(String outDestinationFrom) {
        WaitHelper.waitIsElementClickable(driver, outFromField);
        outFromField.click();
        outFromField.sendKeys(outDestinationFrom);
        bookAflightLocator.click();
        String text = outFromField.getAttribute("value");
        LOG.info(String.format("Outbound Destination From on site = %s", text));
        LOG.info(String.format("Outbound Destination From checked = %s", outDestinationFrom));
        return text;
    }

    public String fillToInboundField(String inDestinationTo) {
        WaitHelper.waitIsElementClickable(driver, inToField);
        inToField.click();
        inToField.sendKeys(inDestinationTo);
        bookAflightLocator.click();
        String text = inToField.getAttribute("value");
        LOG.info(String.format("Inbound Destination To on site = %s", text));
        LOG.info(String.format("Inbound Destination To checked = %s", inDestinationTo));
        return text;
    }

    public String fillFromInboundField(String inDestinationFrom) {
        inFromField.click();
        inFromField.sendKeys(inDestinationFrom);
        bookAflightLocator.click();
        String text = inFromField.getAttribute("value");
        LOG.info(String.format("Inbound Destination From on site = %s", text));
        LOG.info(String.format("Inbound Destination From checked = %s", inDestinationFrom));
        return text;
    }

    public String fillOutDateField(String outdepartDate) {
        outDateField.click();
        outDateField.clear();
        outDateField.sendKeys(outdepartDate);
        bookAflightLocator.click();
        String text = outDateField.getAttribute("value");
        LOG.info(String.format("Outbound flight date = %s", outdepartDate));
        LOG.info(String.format("Outbound flight date on site = %s", text));
        return text;
    }

    public String fillInDateField(String indepartDate) {
        inDateField.click();
        inDateField.clear();
        inDateField.sendKeys(indepartDate);
        bookAflightLocator.click();
        String text = inDateField.getAttribute("value");
        LOG.info(String.format("Inbound flight date = %s", indepartDate));
        LOG.info(String.format("Inbound flight date on site = %s", text));
        return text;
    }

    public Boolean getMessage() {
        WaitHelper.waitIsElementVisibilityOf(driver, errorMessageLocator);
        String textErrorOnSite = errorMessageLocator.getText();
        LOG.info(String.format("Error message = %s", textError));
        LOG.info(String.format("Error message on site = %s", textErrorOnSite));
        return (textError.equals(textErrorOnSite));
    }

    /*
    METHOD CHECK IS ONE FLIGHT DISPLAYED WITH 'FROM' AND 'PRICE'
    */
    public Boolean isFlightPresent() {
        assertTrue(isElementPresent(locator));
        String textOnSite = driver.findElement(locator).getText();
        LOG.info(String.format("Text field From on site = %s", textOnSite));
        if ("From".equals(textOnSite.trim())) {
            LOG.info("A Flight is Displayed");
            return true;
        } else {
            LOG.info("A Flight is NOT Displayed");
            fail("A Flight is NOT Displayed");
            return false;
        }
    }

    private boolean isElementPresent(By by) {
        try {
            WaitHelper.waitIsElementPresence(driver, by);
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void findTitleOutboundFlight() {
        assertTrue(isElementPresent(outboundFlightTitleLocator));
        LOG.info("Title Outbound Flight is Displayed");
    }

    public void findTitleInboundFlight() {
        assertTrue(isElementPresent(inboundFlightTitleLocator));
        LOG.info("Title Inbound Flight is Displayed");
    }

    /*
     METHOD SELECT ONE DEFAULT (BLUE) FLIGHT FROM 7 DAYS OUTBOUND FLIGHTS
     */
    public void clickSelectOutboundFlight() {
        WaitHelper.waitIsElementPresence(driver, outboundFlightSelectBtn);
        WebElement element = driver.findElement(outboundFlightSelectBtn);
        WaitHelper.waitIsElementClickable(driver, element);
        element.submit();
        LOG.info("Click select outbound flight button");
        WaitHelper.waitIsElementPresence(driver, outboundLocator);
    }

    /*
     METHOD SELECT ONE DEFAULT (BLUE) FLIGHT FROM 7 DAYS INBOUND FLIGHTS
     */
    public void clickSelectInboundFlight() {
        WaitHelper.waitIsElementPresence(driver, inboundFlightSelectBtn);
        WebElement element = driver.findElement(inboundFlightSelectBtn);
        WaitHelper.waitIsElementClickable(driver, element);
        element.submit();
        LOG.info("Click select inbound flight button");
        WaitHelper.waitIsElementPresence(driver, inboundLocator);
    }

    /*
    METHOD GETS ONE FIRST FLIGHT FROM 7 DAYS OUTBOUND FLIGHTS
     */
    public void selectOutboundFlight() {
        WebElement fromElement = null;
        WaitHelper.waitVisibilityOfAllElements(driver, driver.findElements(outboundFlightLocator));
        List<WebElement> elementList = driver.findElements(outboundFlightLocator);
        for (int i = 0; i < elementList.size(); i++) {
            try {
                WebElement el = elementList.get(i).findElement(By.xpath("div/div/span[1]"));
                if (el.getAttribute("class").equals("price")) {
                    fromElement = driver.findElement(By.xpath("//section[@class='flight outbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li[" + (i + 1) + "]/div/div/span[1]"));
                    break;
                }
            } catch (Exception e) {
                LOG.info("No outbound flight message 'grey empty field'");
            }
        }
        if (fromElement == null) {
            fail("Flight is not found");
        }
        fromElement.click();
        LOG.info("Choose firth outbound flight");
        WaitHelper.waitIsElementPresence(driver, By.xpath("//section[@class='flight outbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li//div[contains(@class, 'is-selected')]"));
        WaitHelper.waitSeconds(5000);
        WebElement element = driver.findElement(outboundFlightSelectBtn);
        element.click();
        LOG.info("Click select outbound flight button");
        WaitHelper.waitIsElementPresence(driver, outboundLocator);
    }

    /*
     METHOD GETS ONE LAST FLIGHT FROM 7 DAYS INBOUND FLIGHTS
    */
    public void selectInboundFlight() {
        WebElement toElement = null;
        WaitHelper.waitVisibilityOfAllElements(driver, driver.findElements(inboundFlightLocator));
        List<WebElement> elementList = driver.findElements(inboundFlightLocator);
        for (int i = 0; i < elementList.size(); i++) {
            try {
                WebElement el = elementList.get(i).findElement(By.xpath("div/div/span[1]"));
                if (el.getAttribute("class").equals("price")) {
                    toElement = driver.findElement(By.xpath("//section[@class='flight inbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li[" + (i + 1) + "]/div/div/span[1]"));
                    // break;
                }
            } catch (Exception e) {
                LOG.info("No inbound flight message 'grey empty field'");
            }
        }
        if (toElement == null) {
            fail("Flight is not found");
        }
        toElement.click();
        LOG.info("Choose  inbound flight");
        WaitHelper.waitIsElementPresence(driver, By.xpath("//section[@class='flight inbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li//div[contains(@class, 'is-selected')]"));
        WaitHelper.waitSeconds(5000);
        WebElement element = driver.findElement(inboundFlightSelectBtn);
        element.sendKeys(Keys.PAGE_DOWN);
        element.click();
        LOG.info("Click select inbound flight button");
        WaitHelper.waitIsElementPresence(driver, inboundLocator);
    }

    public ChooseAFarePage clickBtnNext() {
        nextBtn.click();
        LOG.info("Click 'NEXT' button");
        return new ChooseAFarePage(driver);
    }

    public void clickSearchBtn() {
        WaitHelper.waitIsElementClickable(driver, searchButton);
        searchButton.click();
        LOG.info("Click 'SEARCH' button");
        WaitHelper.waitIsElementPresence(driver, locator);
        WaitHelper.waitIsElementClickable(driver, nextBtn);
    }
}
