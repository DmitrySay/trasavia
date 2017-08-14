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
import java.util.concurrent.TimeUnit;

public class ServicePage extends BasePage {
    private static final Logger LOG = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//div[@id='top']/div/div[9]/section/div/div/div/div[2]/div/p/iframe")
    private WebElement iframeLocator;

    @FindBy(xpath = "//div[@class='ytp-title-text']/a")
    private WebElement videoNameLocator;

    public ServicePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LOG.info("Get Access to Service Page");
    }

    public String getvideoLinkOnsite() {
        driver.switchTo().frame(iframeLocator);
        String videoLinkOnsite = videoNameLocator.getAttribute("href");
        LOG.info(String.format("Video link on site = %s", videoLinkOnsite));
        driver.switchTo().defaultContent();
        return videoLinkOnsite;
    }

    public String getvideoNameOnsite() {
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
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", videoNameLocator);
        LOG.info("Click on Video link");

        String newWindow = (new WebDriverWait(driver, 30))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindow);
        WaitHelper.waitSeconds(5000);
        WebElement videoNameOnYoutube = driver.findElement(By.xpath("//span[@id='eow-title']"));
        String vnameOnYoutube = videoNameOnYoutube.getText();
        LOG.info(String.format("Video name on youtube = %s", vnameOnYoutube));
        LOG.info(String.format("Video name on site = %s", videoName));
        WebElement videoAuthorOnYoutube = driver.findElement(By.xpath("//div[@id='watch7-user-header']/div/a"));
        String vAuthorOnYoutube = videoAuthorOnYoutube.getText();
        LOG.info(String.format("Video author on youtube = %s", vAuthorOnYoutube));
        LOG.info(String.format("Video author on site = %s", videoAuthor));
        driver.close();
        driver.switchTo().window(originalWindow);
        Assert.assertEquals(videoName, vnameOnYoutube);
        Assert.assertEquals(videoAuthor, vAuthorOnYoutube);

    }

}
