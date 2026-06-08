package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By productsTitle = By.cssSelector("[data-test='title']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        return isDisplayed(productsTitle)
                && isDisplayed(inventoryContainer)
                && isDisplayed(shoppingCartLink);
    }

    public String getTitleText() {
        return getText(productsTitle);
    }

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    public CartPage goToCart() {
        click(shoppingCartLink);
        return new CartPage(driver);
    }


}