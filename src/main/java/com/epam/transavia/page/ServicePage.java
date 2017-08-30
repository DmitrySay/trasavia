package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class ServicePage extends BasePage {
    private static final Logger LOG = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//div[@id='top']/div/div[9]/section/div/div/div/div[2]/div/p/iframe")
    private WebElement iframeLocator;

    @FindBy(xpath = "//div[@class='ytp-title-text']/a")
    private WebElement videoNameLocator;

    public ServicePage(WebDriver driver) {
        super(driver);
        WaitHelper.waitFeedbackLogo(driver, 15);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Service Page");
    }

    public String getvideoLinkOnsite() {
        WaitHelper.waitIsElementClickable(driver, iframeLocator);
        driver.switchTo().frame(iframeLocator);
        String videoLinkOnsite = videoNameLocator.getAttribute("href");
        LOG.info(String.format("Video link on site = %s", videoLinkOnsite));
        driver.switchTo().defaultContent();
        return videoLinkOnsite;
    }

    public String getvideoNameOnsite() {
        WaitHelper.waitIsElementClickable(driver, iframeLocator);
        driver.switchTo().frame(iframeLocator);
        String videoNameOnsite = videoNameLocator.getText();
        LOG.info(String.format("Video name on site = %s", videoNameOnsite));
        driver.switchTo().defaultContent();
        return videoNameOnsite;
    }

    public void checkVideoNameAndAuthorOnTheVideoPage(String videoName, String videoAuthor) {
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        driver.switchTo().frame(iframeLocator);
        WaitHelper.waitIsElementClickable(driver, videoNameLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", videoNameLocator);
        LOG.info("Click on Video link");

        String newWindow = (new WebDriverWait(driver, 30))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ? newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindow);

        WebElement videoNameOnYoutube;
        WebElement videoAuthorOnYoutube;
        try {
            WaitHelper.waitIsElementPresence(driver, By.xpath("//span[@id='eow-title']"));
            videoNameOnYoutube = driver.findElement(By.xpath("//span[@id='eow-title']"));
            videoAuthorOnYoutube = driver.findElement(By.xpath("//div[@id='watch7-user-header']/div/a"));
        } catch (Exception e) {
            WaitHelper.waitIsElementPresence(driver, By.xpath("//div[@id='container']/h1"));
            videoNameOnYoutube = driver.findElement(By.xpath("//div[@id='container']/h1"));
            videoAuthorOnYoutube = driver.findElement(By.xpath("//div[@id='owner-container']//a"));
        }

        String vnameOnYoutube = videoNameOnYoutube.getText();
        LOG.info(String.format("Video name on youtube = %s", vnameOnYoutube));
        LOG.info(String.format("Video name on site = %s", videoName));

        String vAuthorOnYoutube = videoAuthorOnYoutube.getText();
        LOG.info(String.format("Video author on youtube = %s", vAuthorOnYoutube));
        LOG.info(String.format("Video author on site = %s", videoAuthor));
        driver.close();
        driver.switchTo().window(originalWindow);
        Assert.assertEquals(videoName, vnameOnYoutube);
        Assert.assertEquals(videoAuthor, vAuthorOnYoutube);
    }

}
