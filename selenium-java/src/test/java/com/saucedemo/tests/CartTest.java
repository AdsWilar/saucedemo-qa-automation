package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);

        productsPage = loginPage.loginAs(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Products page should be displayed after login"
        );
    }

    @Test
    public void shouldDisplayAddedProductInCart() {
        productsPage.addBackpackToCart();

        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page should be displayed"
        );

        Assert.assertEquals(
                cartPage.getTitleText(),
                "Your Cart",
                "Cart page title should be correct"
        );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain one product"
        );

        Assert.assertEquals(
                cartPage.getItemName(),
                "Sauce Labs Backpack",
                "Product name should be correct"
        );

        Assert.assertEquals(
                cartPage.getItemPrice(),
                "$29.99",
                "Product price should be correct"
        );

        Assert.assertEquals(
                cartPage.getItemQuantity(),
                "1",
                "Product quantity should be one"
        );
    }

    @Test
    public void shouldRemoveProductFromCart() {
        productsPage.addBackpackToCart();

        CartPage cartPage = productsPage.goToCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain one product before removing it"
        );

        cartPage.removeBackpackFromCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart should be empty after removing the product"
        );
    }
}