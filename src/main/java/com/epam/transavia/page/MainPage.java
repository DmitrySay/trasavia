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

    @FindBy(id = "dateSelection_IsReturnFlight-datepicker")
    private WebElement returnOnDateField;

    @FindBy(id = "dateSelection_IsReturnFlight")
    private WebElement returnOnCheckbox;

//    @FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[1]/div/div/div[2]/div/div/button[2]")
//    private WebElement buttonAdultsPlus;

    @FindBy(linkText = "Save")
    private WebElement savePassengerButton;

    @FindBy(linkText = "Search")
    private WebElement searchFlightButton;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkIsMainPageOpened(String checkedHeadline) {
        try {
            String headlineOnsite = linkText.getText();
            LOG.info(String.format("headlineOnsite = %s", headlineOnsite));
            LOG.info(String.format("checkedHeadline = %s", checkedHeadline));
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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillFromField(String destinationFrom) {
        try {
            WebElement element = null;
            String s,destinationFromOnsite="";
            fromField.click();
            Thread.sleep(1000);
            List<WebElement> dropdownsFrom = driver.findElements(By.xpath("//ol[@class='results']/descendant::li"));
            for (int i = 0; i < dropdownsFrom.size(); i++) {
                s = dropdownsFrom.get(i).getText();
                if (destinationFrom.equals(s)) {
                    element = driver.findElement(By.xpath("//ol[@class='results']/li[" + (i + 1) + "]"));
                    destinationFromOnsite= element.getText();
                    LOG.info(String.format("destinationFromOnsite = %s", element.getText()));
                    LOG.info(String.format("destinationFrom = %s", destinationFrom));
                }
            }
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Assert.assertEquals(destinationFrom, destinationFromOnsite);
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillToField(String destinationTo) {
        try {
            WebElement element = null;
            String s,destinationToOnsite="";
            toField.click();
            Thread.sleep(1000);
            List<WebElement> dropdownsTo = driver.findElements(By.xpath("//ol[@class='results']/li[2]/ol/descendant::li"));
            for (int i = 0; i < dropdownsTo.size(); i++) {
                s = dropdownsTo.get(i).getText();
                if (destinationTo.equals(s)) {
                    element = driver.findElement(By.xpath("//ol[@class='results']/li[2]/ol/li[" + (i + 1) + "]"));
                    destinationToOnsite= element.getText();
                    LOG.info(String.format("destinationToOnsite = %s", element.getText()));
                    LOG.info(String.format("destinationTo = %s", destinationTo));
                }
            }
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Assert.assertEquals(destinationTo, destinationToOnsite);
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    //6 Aug 2017
    public void fillDepartOnDateField(String departDate){
        departOnDateField.sendKeys(departDate);
    }

}
