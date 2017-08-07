package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;


public class BookAFlightPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BookAFlightPage.class);
    private static final By locator = By.xpath("//span[@class='price']");
    private static final By outboundFlightTitleLocator = By.xpath("//h2[contains(text(),'Outbound flight')]/text()");
    private static final By inboundFlightTitleLocator = By.xpath("//h2[contains(text(),'Inbound flight')]/text()");
    private static final String textError = "Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again.";
    //выбор рейса
    private static final By outboundFlightLocator = By.xpath("//section[@class='flight outbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li");
    private static final By inboundFlightLocator = By.xpath("//section[@class='flight inbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/descendant::li");

    //select вылет
    private static final By outboundFlightSelectBtn = By.xpath("//section[@class='flight outbound']/div/div[1]/div[2]/div/div[3]/div/form/div/button");
    private static final By inboundFlightSelectBtn = By.xpath("//section[@class='flight inbound']/div/div[1]/div[2]/div/div[3]/div/form/div/button");

    @FindBy(name = "next_button")
    private WebElement nextBtn;

    @FindBy(xpath = "//form[@id='flights']/div/section/div[2]/div[1]/div/div/div[1]/div/div/div[2]/p")
    private WebElement errorMessageLocator;

    public BookAFlightPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean getMessage() {
        String textErrorOnSite = errorMessageLocator.getText();
        LOG.info(String.format("Error message = %s", textError));
        LOG.info(String.format("Error message on site = %s", textErrorOnSite));
        return (textError.equals(textErrorOnSite));
    }

    public void isFlightPresent() {
        assertTrue(isElementPresent(locator));
        LOG.info("A Flight is Displayed");
    }

    private boolean isElementPresent(By by) {
        try {
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

    public void selectOutboundFlight() {
        try {
            WebElement fromElement = null;
            List<WebElement> elementList = driver.findElements(outboundFlightLocator);
            for (int i = 0; i < elementList.size(); i++) {
                WebElement el = elementList.get(i).findElement(By.xpath("div/div/span[1]"));
                if (el.getAttribute("class").equals("price")) {
                    fromElement = driver.findElement(By.xpath("//section[@class='flight outbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li[" + (i + 1) + "]/div/div/span[1]"));
                    break;
                }
            }
            if (fromElement == null) {
                fail("Flight is not found");
            }
            fromElement.click();
            LOG.info("Choose firth outbound flight");
            Thread.sleep(3000);
            driver.findElement(outboundFlightSelectBtn).click();
            LOG.info("Click select outbound flight button");
            Thread.sleep(3000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void selectInboundFlight() {
        try {
            WebElement toElement = null;
            List<WebElement> elementList = driver.findElements(inboundFlightLocator);
            for (int i = 0; i < elementList.size(); i++) {
                WebElement el = elementList.get(i).findElement(By.xpath("div/div/span[1]"));
                if (el.getAttribute("class").equals("price")) {
                    toElement = driver.findElement(By.xpath("//section[@class='flight inbound']/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li[" + (i + 1) + "]/div/div/span[1]"));
                    // break;
                }
            }
            if (toElement == null) {
                fail("Flight is not found");
            }
            toElement.click();
            LOG.info("Choose  inbound flight");
            Thread.sleep(3000);
            driver.findElement(inboundFlightSelectBtn).click();
            LOG.info("Click select inbound flight button");
            Thread.sleep(3000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickBtnNext() {
        try {
            nextBtn.click();
            LOG.info("Click 'NEXT' button");
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }
}
