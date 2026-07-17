package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(usernameInput)
                && isDisplayed(passwordInput)
                && isDisplayed(loginButton);
    }

    public boolean isLoginButtonEnabled() {
        return isEnabled(loginButton);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public ProductsPage clickLoginButton() {
        click(loginButton);
        return new ProductsPage(driver);
    }

    public ProductsPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

   }