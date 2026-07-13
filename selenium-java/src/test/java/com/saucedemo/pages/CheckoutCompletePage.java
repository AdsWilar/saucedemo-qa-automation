package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By title = By.cssSelector("[data-test='title']");
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By completeText = By.cssSelector("[data-test='complete-text']");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutCompletePageDisplayed() {
        return isDisplayed(title)
                && isDisplayed(completeHeader)
                && isDisplayed(completeText)
                && isDisplayed(backHomeButton);
    }

    public String getTitleText() {
        return getText(title);
    }

    public String getCompleteHeaderText() {
        return getText(completeHeader);
    }

    public String getCompleteMessageText() {
        return getText(completeText);
    }

    public ProductsPage backHome() {
        click(backHomeButton);
        return new ProductsPage(driver);
    }
}