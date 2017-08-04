package com.epam.transavia.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ChooseAFarePage extends BasePage {
    private static final Logger LOG = Logger.getLogger(ChooseAFarePage.class);

    @FindBy(xpath = "//div[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/thead/tr/th[3]/span[1]")
    private WebElement plusTitle;

    // Price per person € 143
    @FindBy(xpath = "//div[@id='top']/div[1]/div[1]/div/div/div/div/div[2]/table/tfoot/tr/td[2]/p/span/")
    private WebElement pricePerPerson;

    // +  € 50
    @FindBy(xpath = "//div[@id='top']/div[1]/div[1]/div/div/div/div/div[2]/table/thead/tr/th[3]/span[2]")
    private WebElement extraPricePerPerson;

    @FindBy(xpath = "//div[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/tfoot/tr/td[3]/div/div/button[2]")
    private WebElement selectedPlusBtn;

    //total  € 579.
    @FindBy(xpath = "//div[@id='top']/div[2]/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]/text()")
    private WebElement totalCostBeforeDot;

    //центы 00
    @FindBy(xpath = "//div[@id='top']/div[2]/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]/span")
    private WebElement totalCostAfterDot;


    public ChooseAFarePage(WebDriver driver) {
        super(driver);
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

    public int getPricefromElement(WebElement element) {
        String s = element.getText();
        LOG.info(String.format("string with price = %s", s));
        String[] s2;
        s2 = s.split("\\D+");
        LOG.info(String.format("string after split = %s", s2));
        int quantity = 0;
        quantity = Integer.parseInt(s2[1]);
        return quantity;
    }

    public void checkCorrectCulculations() {
        double priceOnePerson = getPricefromElement(pricePerPerson);
        LOG.info(String.format("price One Person = %d", priceOnePerson));

        double extraPriceOnePerson = getPricefromElement(extraPricePerPerson);
        LOG.info(String.format(" extra Price One Person = %d", extraPriceOnePerson));

        double totalCostBefore = getPricefromElement(totalCostBeforeDot);
        LOG.info(String.format(" total Cost Before = %d", totalCostBefore));

        double totalCostAfter = getPricefromElement(totalCostAfterDot);
        LOG.info(String.format(" total Cost After = %d", totalCostAfter));

        double culculate = 3 * priceOnePerson + 3 * extraPriceOnePerson;
        double total = totalCostBefore + totalCostAfter;

        LOG.info(String.format(" culculate = %d", culculate));
        LOG.info(String.format(" total = %d", total));

        Assert.assertEquals(total, culculate);

    }
}
