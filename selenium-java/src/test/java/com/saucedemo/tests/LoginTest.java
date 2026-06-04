package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void shouldDisplayLoginPage() {
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "Login page should be displayed"
        );

        Assert.assertTrue(
                loginPage.isLoginButtonEnabled(),
                "Login button should be enabled"
        );
    }

    @Test
    public void shouldLoginSuccessfullyWithStandardUser() {
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginAs(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Products page should be displayed after successful login"
        );

        Assert.assertEquals(
                productsPage.getTitleText(),
                "Products",
                "Products page title should be correct"
        );
    }

    @Test
    public void shouldShowErrorMessageWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should indicate invalid credentials"
        );
    }

    @Test
    public void shouldShowErrorMessageWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Sorry, this user has been locked out"),
                "Error message should indicate locked out user"
        );
    }
}