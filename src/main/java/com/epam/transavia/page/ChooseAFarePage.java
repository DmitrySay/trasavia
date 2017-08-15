package com.epam.transavia.page;

import com.epam.transavia.util.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChooseAFarePage extends BasePage {
    private static final Logger LOG = Logger.getLogger(ChooseAFarePage.class);

    @FindBy(xpath = "//div[@id='top']/div[1]/div[1]/div/div/h1")
    private WebElement checkTitle;

    @FindBy(xpath = "//thead/tr/th[3]")
    private WebElement plusTitle;

    @FindBy(xpath = "//tfoot/tr/td[2]/p/span")
    private WebElement pricePerPerson;

    @FindBy(xpath = "//thead/tr/th[3]/span[2]")
    private WebElement extraPricePerPerson;

    @FindBy(xpath = "//tfoot/tr/td[3]/div/div/button[2]")
    private WebElement selectedPlusBtn;

    @FindBy(xpath = "//footer/div/div/section/div/div/div[2]/div/div/div[2]")
    private WebElement totalCostBeforeDot;

    @FindBy(xpath = "//footer/div/div/section/div/div/div[2]/div/div/div[1]")
    private WebElement totalCostBeforeDotBasic;

    @FindBy(xpath = "//footer/div/div/section/div/div/div[2]/div/div/div[2]/span")
    private WebElement totalCostAfterDot;

    @FindBy(xpath = "//p[@class='price']")
    private WebElement totalCostlocator;

    @FindBy(xpath = "//footer/div/div/a/section/p/span")
    private WebElement detailsLinklocator;

    @FindBy(xpath = "//aside/div/div[2]/p[1]")
    private WebElement outboundPricelocator;

    @FindBy(xpath = "//aside/div/div[5]/p[1]")
    private WebElement inboundPricelocator;

    public ChooseAFarePage(WebDriver driver) {
        super(driver);
        WaitHelper.waitFeedbackLogo(driver, 20);
        PageFactory.initElements(driver, this);
        LOG.info("Get Access to Choose A Fare Page");
        Assert.assertEquals("Get more out of your trip! ", checkTitle.getText());
    }

    public void clickPlusTitle() {
        WaitHelper.waitIsElementClickable(driver, plusTitle);
        plusTitle.click();
        LOG.info("Click Plus Title");
        WaitHelper.waitSeconds(3000);
    }

    public void clickBtnSelectInPlusTab() {
        WaitHelper.waitIsElementClickable(driver, selectedPlusBtn);
        selectedPlusBtn.click();
        LOG.info("Click 'select' button in 'plus' tab");
    }

    /*
    Method cut price from String text and convert to double
     */
    private double getPricefromElement(WebElement element) {
        String s = element.getText();
        LOG.info(String.format("String with price on site = %s", s));
        String[] s2 = {" ", " "};
        s2 = s.split("\\D+");
        LOG.info(String.format("String after split = %s", s2[1]));
        double quantity = 0;
        quantity = Double.parseDouble(s2[1]);
        return quantity;
    }

    /*
    Method gets price for one person, gets extra cost from tab 'PLUS' and gets total amount.
    Check correct arithmetic, If 3*(price_adult+price_children)+extra*3 = total Test is positive.
     */
    public void checkCorrectCalculations() {
        double priceOnePerson = getPricefromElement(pricePerPerson);
        LOG.info(String.format("Price One Person = %.2f", priceOnePerson));
        double extraPriceOnePerson = getPricefromElement(extraPricePerPerson);
        LOG.info(String.format(" Extra Price One Person = %.2f", extraPriceOnePerson));
        double totalCostBefore = getPricefromElement(totalCostBeforeDot);
        LOG.info(String.format(" Total price Before = %.2f", totalCostBefore));
        double calculate = 3 * priceOnePerson + 3 * extraPriceOnePerson;
        double total = totalCostBefore;
        LOG.info(String.format("Calculate price = %.2f", calculate));
        LOG.info(String.format("Total price = %.2f", total));
        Assert.assertEquals(total, calculate);
        clickBtnSelectInPlusTab();
        double totalPrice = getPricefromElement(totalCostlocator);
        LOG.info(String.format("Total price after click 'select' button = %.2f", totalPrice));
        Assert.assertEquals(totalPrice, calculate);
    }

    /*
    Method gets total price
     */
    public double getTotalPrice() {
        WaitHelper.waitIsElementVisibilityOf(driver, totalCostBeforeDotBasic);
        double totalCostBefore = getPricefromElement(totalCostBeforeDotBasic);
        LOG.info(String.format("Total price on site = %.2f", totalCostBefore));
        return totalCostBefore;
    }

    public double getDetailPrice() {
        WaitHelper.waitIsElementClickable(driver, detailsLinklocator);
        detailsLinklocator.click();
        WaitHelper.waitIsElementVisibilityOf(driver, outboundPricelocator);
        double outboundPrice = getPricefromElement(outboundPricelocator);
        LOG.info(String.format("Outbound flight price on site = %.2f", outboundPrice));
        double intboundPrice = getPricefromElement(inboundPricelocator);
        LOG.info(String.format("Inbound flight price on site = %.2f", intboundPrice));
        double total = outboundPrice + intboundPrice;
        LOG.info(String.format("Total price on site in detail tab = %.2f", total));
        return total;
    }
}
