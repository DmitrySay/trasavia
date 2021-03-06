package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@id='retrieveBookingByLastname_RecordLocator']")
    private WebElement bookingNumberField;

    @FindBy(xpath = "//input[@id='retrieveBookingByLastname_LastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='retrieveBookingByLastname_FlightDate-datepicker']")
    private WebElement flightDateField;

    @FindBy(xpath = "//form[@id='access-booking']/div/div/div[4]/button")
    private WebElement viewBookingBtn;


    public LoginPage(WebDriver driver) {
        super(driver);
        WaitHelper.waitFeedbackLogo(driver, 15);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Login Page");
    }

    public String getloginPageTitle() {
        WaitHelper.waitTitleIs(driver, "Log in");
        String actualTitle = driver.getTitle();
        LOG.info(String.format("Login Page Title = %s", actualTitle));
        return actualTitle;
    }

    public BookingOverviewPage doLogin(String bookingNumber, String lastName, String flightDate) {
            bookingNumberField.click();
            bookingNumberField.sendKeys(bookingNumber);
            LOG.info(String.format("Enter booking = %s", bookingNumber));
            lastNameField.click();
            lastNameField.sendKeys(lastName);
            LOG.info(String.format("Enter last name = %s", lastName));
            flightDateField.click();
            flightDateField.sendKeys(flightDate);
            LOG.info(String.format("Enter flight date = %s", flightDate));
            bookingNumberField.click();
            bookingNumberField.click();
            viewBookingBtn.submit();
            LOG.info("Click 'View booking' Button ");
            return new BookingOverviewPage(driver);
    }

}
