package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChooseAFarePage extends BasePage {
    private static final Logger LOG = Logger.getLogger(ChooseAFarePage.class);

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

    @FindBy(xpath = "//footer/div/div/section/div/div/div[2]/div/div/div[2]/span")
    private WebElement totalCostAfterDot;

    @FindBy(xpath = "//p[@class='price']")
    private WebElement totalCostlocator;

    public ChooseAFarePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPlusTitle() {
        try {
            plusTitle.click();
            LOG.info("Click Plus Title");
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }

    }

    public void clickBtnSelectInPlusTab() {
        try {
            selectedPlusBtn.click();
            LOG.info("Click 'select' button in 'plus' tab");
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    Method cut price from String text and convert to double
     */
    private double getPricefromElement(WebElement element) {
        String s = element.getText();
        LOG.info(String.format("String with price on site = %s", s));
        String[] s2;
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
}
