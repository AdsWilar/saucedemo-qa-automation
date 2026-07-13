package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By title = By.cssSelector("[data-test='title']");
    private final By itemName = By.className("inventory_item_name");
    private final By itemDescription = By.className("inventory_item_desc");
    private final By itemPrice = By.className("inventory_item_price");
    private final By itemQuantity = By.className("cart_quantity");

    private final By paymentInformationLabel = By.cssSelector("[data-test='payment-info-label']");
    private final By paymentInformationValue = By.cssSelector("[data-test='payment-info-value']");

    private final By shippingInformationLabel = By.cssSelector("[data-test='shipping-info-label']");
    private final By shippingInformationValue = By.cssSelector("[data-test='shipping-info-value']");

    private final By priceTotalLabel = By.cssSelector("[data-test='total-info-label']");
    private final By itemTotal = By.cssSelector("[data-test='subtotal-label']");
    private final By tax = By.cssSelector("[data-test='tax-label']");
    private final By total = By.cssSelector("[data-test='total-label']");

    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        return isDisplayed(title)
                && isDisplayed(itemName)
                && isDisplayed(itemPrice)
                && isDisplayed(itemQuantity)
                && isDisplayed(finishButton)
                && isDisplayed(cancelButton);
    }

    public String getTitleText() {
        return getText(title);
    }

    public String getItemName() {
        return getText(itemName);
    }

    public String getItemDescription() {
        return getText(itemDescription);
    }

    public String getItemPrice() {
        return getText(itemPrice);
    }

    public String getItemQuantity() {
        return getText(itemQuantity);
    }

    public String getPaymentInformationLabel() {
        return getText(paymentInformationLabel);
    }

    public String getPaymentInformationValue() {
        return getText(paymentInformationValue);
    }

    public String getShippingInformationLabel() {
        return getText(shippingInformationLabel);
    }

    public String getShippingInformationValue() {
        return getText(shippingInformationValue);
    }

    public String getPriceTotalLabel() {
        return getText(priceTotalLabel);
    }

    public String getItemTotal() {
        return getText(itemTotal);
    }

    public String getTax() {
        return getText(tax);
    }

    public String getTotal() {
        return getText(total);
    }

    public CheckoutCompletePage finishCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}