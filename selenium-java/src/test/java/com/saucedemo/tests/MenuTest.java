package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.MenuPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

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
    public void shouldOpenMenu() {
        MenuPage menuPage = productsPage.openMenu();

        Assert.assertTrue(
                menuPage.isMenuDisplayed(),
                "Menu should display all expected options"
        );
    }

    @Test
    public void shouldCloseMenu() {
        MenuPage menuPage = productsPage.openMenu();

        Assert.assertTrue(
                menuPage.isMenuDisplayed(),
                "Menu should be displayed before closing it"
        );

        menuPage.closeMenu();
        Assert.assertTrue(
                menuPage.isMenuClosed(),
                "Menu should be closed after clicking close button"
        );
    }

    @Test
    public void shouldLogoutSuccessfully() {
        MenuPage menuPage = productsPage.openMenu();

        LoginPage loginPage = menuPage.logout();

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "Login page should be displayed after logout"
        );
    }

    @Test
    public void shouldResetAppStateAndClearCartBadge() {
        productsPage.addBackpackToCart();

        Assert.assertTrue(
                productsPage.isCartBadgeDisplayed(),
                "Cart badge should be displayed after adding a product"
        );

        Assert.assertEquals(
                productsPage.getCartBadgeText(),
                "1",
                "Cart badge should show one product before reset"
        );

        MenuPage menuPage = productsPage.openMenu();

        menuPage.resetAppState();
        menuPage.closeMenu();

        Assert.assertFalse(
                productsPage.isCartBadgeDisplayed(),
                "Cart badge should not be displayed after resetting app state"
        );

        Assert.assertEquals(
                productsPage.getCartBadgeCount(),
                0,
                "Cart badge count should be zero after resetting app state"
        );
    }
}