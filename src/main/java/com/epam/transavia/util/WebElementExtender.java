package com.epam.transavia.util;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class WebElementExtender {

    public static void highlightElement(WebElement element) {
        WrapsDriver wrappedElement = (WrapsDriver) element;
        JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
        for (int i = 0; i < 100; i++) {
            driver.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 3px solid red;");
        }
        driver.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }
}


