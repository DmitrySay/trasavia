package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class MainPage extends BasePage {
    public static final String URL = "https://www.transavia.com/en-EU/home/";
    private static final Logger LOG = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//form[@id='desktop']/section/div[1]/h1")
    private WebElement linkText;

    @FindBy(id = "routeSelection_DepartureStation-input")
    private WebElement fromField;

    @FindBy(xpath = "//div[@class='cc-left']/button")
    private WebElement iunderstendButton;

    @FindBy(id = "routeSelection_ArrivalStation-input")
    private WebElement toField;

    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement departOnDateField;

    @FindBy(id = "dateSelection_IsReturnFlight")
    private WebElement returnOnCheckbox;

    @FindBy(xpath = "//form[@id='desktop']/section/div[3]/div/button")
    private WebElement searchFlightButton;

    @FindBy(xpath = "//input[@id='booking-passengers-input']")
    private WebElement whoWillBeTravellingField;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkIsMainPageOpened(String checkedHeadline) {
        try {
            String headlineOnsite = linkText.getText();
            LOG.info(String.format("Headline on site = %s", headlineOnsite));
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
            Thread.sleep(5000);
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
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Assert.assertEquals(destinationFrom, destinationFromOnsite);
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
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Assert.assertEquals(destinationTo, destinationToOnsite);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillDepartOnDateField(String departDate) {
        departOnDateField.click();
        departOnDateField.clear();
        departOnDateField.sendKeys(departDate);
        LOG.info(String.format("Depart date = %s", departDate));
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
}
