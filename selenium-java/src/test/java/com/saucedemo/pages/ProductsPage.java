package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By productsTitle = By.cssSelector("[data-test='title']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productDescriptions = By.className("inventory_item_desc");
    private final By productPrices = By.className("inventory_item_price");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By sortDropdown = By.className("product_sort_container");

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

    public int getProductCount() {
        return getElementCount(inventoryItems);
    }

    public boolean areProductCardsDisplayed() {
        return getElementCount(productNames) > 0
                && getElementCount(productDescriptions) > 0
                && getElementCount(productPrices) > 0;
    }

    public List<String> getProductNames() {
        return getTexts(productNames);
    }

    public List<Double> getProductPrices() {
        return getTexts(productPrices)
                .stream()
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public void sortByNameAscending() {
        Select select = new Select(waitForVisibility(sortDropdown));
        select.selectByValue("az");
    }

    public void sortByPriceLowToHigh() {
        Select select = new Select(waitForVisibility(sortDropdown));
        select.selectByValue("lohi");
    }

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    public boolean isRemoveBackpackButtonDisplayed() {
        return isVisible(removeBackpackButton);
    }

    public boolean isCartBadgeDisplayed() {
        return isVisible(shoppingCartBadge);
    }

    public String getCartBadgeText() {
        return getText(shoppingCartBadge);
    }
}