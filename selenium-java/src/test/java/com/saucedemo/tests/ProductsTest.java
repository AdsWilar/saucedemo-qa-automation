package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsTest extends BaseTest {

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
    public void shouldDisplayProductsCatalog() {
        Assert.assertEquals(
                productsPage.getTitleText(),
                "Products",
                "Products page title should be correct"
        );

        Assert.assertTrue(
                productsPage.getProductCount() > 0,
                "Products catalog should contain at least one product"
        );

        Assert.assertTrue(
                productsPage.areProductCardsDisplayed(),
                "Product cards should display name, description and price"
        );
    }

    @Test
    public void shouldSortProductsByNameAscending() {
        productsPage.sortByNameAscending();

        List<String> actualProductNames = productsPage.getProductNames();
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);

        Collections.sort(expectedProductNames);

        Assert.assertEquals(
                actualProductNames,
                expectedProductNames,
                "Products should be sorted by name ascending"
        );
    }

    @Test
    public void shouldSortProductsByPriceLowToHigh() {
        productsPage.sortByPriceLowToHigh();

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products should be sorted by price from low to high"
        );
    }

    @Test
    public void shouldAddAndRemoveProductFromCatalog() {
        productsPage.addBackpackToCart();

        Assert.assertTrue(
                productsPage.isCartBadgeDisplayed(),
                "Cart badge should be displayed after adding a product"
        );

        Assert.assertEquals(
                productsPage.getCartBadgeText(),
                "1",
                "Cart badge should show one product"
        );

        Assert.assertTrue(
                productsPage.isRemoveBackpackButtonDisplayed(),
                "Remove button should be displayed after adding the product"
        );

        productsPage.removeBackpackFromCart();

        Assert.assertFalse(
                productsPage.isCartBadgeDisplayed(),
                "Cart badge should not be displayed after removing the product"
        );
    }
}