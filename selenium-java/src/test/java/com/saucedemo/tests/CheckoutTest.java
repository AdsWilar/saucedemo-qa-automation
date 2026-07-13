package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

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

    private CheckoutStepOnePage goToCheckoutStepOnePage() {
        productsPage.addBackpackToCart();

        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page should be displayed"
        );

        return cartPage.checkout();
    }

    @Test
    public void shouldDisplayCheckoutStepOnePage() {
        CheckoutStepOnePage checkoutStepOnePage = goToCheckoutStepOnePage();

        Assert.assertTrue(
                checkoutStepOnePage.isCheckoutStepOnePageDisplayed(),
                "Checkout step one page should be displayed"
        );

        Assert.assertEquals(
                checkoutStepOnePage.getTitleText(),
                "Checkout: Your Information",
                "Checkout step one title should be correct"
        );
    }

    @Test
    public void shouldShowErrorWhenFirstNameIsMissing() {
        CheckoutStepOnePage checkoutStepOnePage = goToCheckoutStepOnePage();

        checkoutStepOnePage.clickContinue();

        Assert.assertTrue(
                checkoutStepOnePage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertEquals(
                checkoutStepOnePage.getErrorMessage(),
                "Error: First Name is required",
                "First name error message should be correct"
        );
    }

    @Test
    public void shouldShowErrorWhenLastNameIsMissing() {
        CheckoutStepOnePage checkoutStepOnePage = goToCheckoutStepOnePage();

        checkoutStepOnePage.enterFirstName("Alejandro");
        checkoutStepOnePage.clickContinue();

        Assert.assertTrue(
                checkoutStepOnePage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertEquals(
                checkoutStepOnePage.getErrorMessage(),
                "Error: Last Name is required",
                "Last name error message should be correct"
        );
    }

    @Test
    public void shouldShowErrorWhenPostalCodeIsMissing() {
        CheckoutStepOnePage checkoutStepOnePage = goToCheckoutStepOnePage();

        checkoutStepOnePage.enterFirstName("Alejandro");
        checkoutStepOnePage.enterLastName("Aroma");
        checkoutStepOnePage.clickContinue();

        Assert.assertTrue(
                checkoutStepOnePage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertEquals(
                checkoutStepOnePage.getErrorMessage(),
                "Error: Postal Code is required",
                "Postal code error message should be correct"
        );
    }

    @Test
    public void shouldCompleteCheckoutSuccessfully() {
        CheckoutStepOnePage checkoutStepOnePage = goToCheckoutStepOnePage();

        CheckoutOverviewPage checkoutOverviewPage =
                checkoutStepOnePage.continueWithValidInformation(
                        "Alejandro",
                        "Aroma",
                        "12345"
                );

        Assert.assertTrue(
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed(),
                "Checkout overview page should be displayed"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getTitleText(),
                "Checkout: Overview",
                "Checkout overview title should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getItemQuantity(),
                "1",
                "Product quantity should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getItemName(),
                "Sauce Labs Backpack",
                "Product name should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getItemPrice(),
                "$29.99",
                "Product price should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getPaymentInformationLabel(),
                "Payment Information:",
                "Payment information label should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getPaymentInformationValue(),
                "SauceCard #31337",
                "Payment information value should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getShippingInformationLabel(),
                "Shipping Information:",
                "Shipping information label should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getShippingInformationValue(),
                "Free Pony Express Delivery!",
                "Shipping information value should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getPriceTotalLabel(),
                "Price Total",
                "Price total label should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getItemTotal(),
                "Item total: $29.99",
                "Item total should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getTax(),
                "Tax: $2.40",
                "Tax should be correct"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getTotal(),
                "Total: $32.39",
                "Total should be correct"
        );

        CheckoutCompletePage checkoutCompletePage =
                checkoutOverviewPage.finishCheckout();

        Assert.assertTrue(
                checkoutCompletePage.isCheckoutCompletePageDisplayed(),
                "Checkout complete page should be displayed"
        );

        Assert.assertEquals(
                checkoutCompletePage.getTitleText(),
                "Checkout: Complete!",
                "Checkout complete title should be correct"
        );

        Assert.assertEquals(
                checkoutCompletePage.getCompleteHeaderText(),
                "Thank you for your order!",
                "Complete header should be correct"
        );

        Assert.assertEquals(
                checkoutCompletePage.getCompleteMessageText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Complete message should be correct"
        );

        ProductsPage productsPageAfterBackHome = checkoutCompletePage.backHome();

        Assert.assertTrue(
                productsPageAfterBackHome.isProductsPageDisplayed(),
                "Products page should be displayed after clicking Back Home"
        );
    }
}