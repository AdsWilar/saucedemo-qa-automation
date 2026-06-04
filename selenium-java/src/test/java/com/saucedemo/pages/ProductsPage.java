package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By productsTitle = By.cssSelector("[data-test='title']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCartLink = By.className("shopping_cart_link");

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
}