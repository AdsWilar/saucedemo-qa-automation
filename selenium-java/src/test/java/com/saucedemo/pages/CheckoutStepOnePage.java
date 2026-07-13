package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By title = By.cssSelector("[data-test='title']");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutStepOnePageDisplayed() {
        return isDisplayed(title)
                && isDisplayed(firstNameInput)
                && isDisplayed(lastNameInput)
                && isDisplayed(postalCodeInput)
                && isDisplayed(continueButton)
                && isDisplayed(cancelButton);
    }

    public String getTitleText() {
        return getText(title);
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public CheckoutOverviewPage continueWithValidInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();

        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessage);
    }
}