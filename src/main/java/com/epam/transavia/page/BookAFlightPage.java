package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;


public class BookAFlightPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BookAFlightPage.class);
    private static final By locator = By.xpath("//span[@class='price']");

    public BookAFlightPage(WebDriver driver) {
        super(driver);
    }

    public void isFlightPresent() {
        assertTrue(isElementPresent(locator));
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
