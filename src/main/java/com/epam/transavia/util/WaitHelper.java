package com.epam.transavia.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitHelper {
    private static final Logger LOG = Logger.getLogger(WaitHelper.class);

    public WaitHelper(WebDriver driver) {
    }

    public static void waitLogoTtransavia(WebDriver driver, long timeOutInSeconds) {
        LOG.info("Wait Logo Transavia");
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("logo-footer")));
    }

    public static void waitFeedbackLogo(WebDriver driver, long timeOutInSeconds) {
        LOG.info("Wait FEEDBACK Logo ");
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='usabilla_live_button_container']/iframe")));
        WebElement element = driver.findElement(By.xpath("//div[@class='usabilla_live_button_container']/iframe"));
        driver.switchTo().frame(element);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("html/body/img")));
        driver.switchTo().defaultContent();
    }

    public static void waitIsElementPresence(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static void waitIsElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitVisibilityOfAllElements(WebDriver driver, List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitIsElementSelected(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public static void waitTitleIs(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitForPageScriptLoad(WebDriver driver) {
        new WebDriverWait(driver, 20).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitSeconds(int time) {
        try {
            Thread.sleep(time);
            LOG.info(String.format("Waiting ... = %s", time));
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

}