package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.pages.CheckoutStepOnePage;

public class CartPage extends BasePage {

    private final By cartTitle = By.cssSelector("[data-test='title']");
    private final By cartItems = By.className("cart_item");
    private final By itemName = By.className("inventory_item_name");
    private final By itemPrice = By.className("inventory_item_price");
    private final By itemQuantity = By.className("cart_quantity");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(cartTitle)
                && isDisplayed(checkoutButton)
                && isDisplayed(continueShoppingButton);
    }

    public String getTitleText() {
        return getText(cartTitle);
    }

    public int getCartItemCount() {
        return getElementCount(cartItems);
    }

    public String getItemName() {
        return getText(itemName);
    }

    public String getItemPrice() {
        return getText(itemPrice);
    }

    public String getItemQuantity() {
        return getText(itemQuantity);
    }

    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    public boolean isBackpackDisplayed() {
        return isVisible(itemName);
    }

    public CheckoutStepOnePage checkout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }
}