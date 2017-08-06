package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BookingOverviewPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BookingOverviewPage.class);

    @FindBy(xpath = "//div[@id='top']/div[1]/div/header/div/p")
    private WebElement bookingNumberLocator;

    @FindBy(xpath = "//div[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[1]")
    private WebElement destinationFromLocator;

    @FindBy(xpath = "//div[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[3]")
    private WebElement destinationToLocator;

    public BookingOverviewPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public String getBookingOverviewPageTitle() {
        String title="";
        try {
            Thread.sleep(5000);
            title = driver.getTitle();
            LOG.info(String.format("Booking Overview Page Title = %s", title));
            return title;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
        return title;
    }

    public String getBookingNumber() {
        LOG.info(String.format("Booking number on site is = %s", bookingNumberLocator.getText()));
        return bookingNumberLocator.getText();
    }

    public String getDestinationFrom() {
        LOG.info(String.format("Destination from on site is = %s", destinationFromLocator.getText()));
        return destinationFromLocator.getText();
    }

    public String getDestinationTo() {
        LOG.info(String.format("Destination to on site is = %s", destinationToLocator.getText()));
        return destinationToLocator.getText();
    }


}
