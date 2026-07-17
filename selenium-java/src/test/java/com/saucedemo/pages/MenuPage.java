package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePage {

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By closeMenuButton = By.id("react-burger-cross-btn");

    private final By menuWrapper = By.className("bm-menu-wrap");

    private final By allItemsLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        click(menuButton);
        waitUntilMenuIsOpen();
    }

    public void closeMenu() {
        click(closeMenuButton);
        waitUntilMenuIsClosed();
    }

    public boolean isMenuDisplayed() {
        return isMenuOpen()
                && isVisible(allItemsLink)
                && isVisible(aboutLink)
                && isVisible(logoutLink)
                && isVisible(resetAppStateLink);
    }

    public boolean isMenuOpen() {
        try {
            return wait.until(
                    ExpectedConditions.attributeToBe(
                            menuWrapper,
                            "aria-hidden",
                            "false"
                    )
            );
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public boolean isMenuClosed() {
        try {
            return wait.until(
                    ExpectedConditions.attributeToBe(
                            menuWrapper,
                            "aria-hidden",
                            "true"
                    )
            );
        } catch (TimeoutException exception) {
            return false;
        }
    }

    private void waitUntilMenuIsOpen() {
        wait.until(
                ExpectedConditions.attributeToBe(
                        menuWrapper,
                        "aria-hidden",
                        "false"
                )
        );
    }

    private void waitUntilMenuIsClosed() {
        wait.until(
                ExpectedConditions.attributeToBe(
                        menuWrapper,
                        "aria-hidden",
                        "true"
                )
        );
    }

    public ProductsPage clickAllItems() {
        click(allItemsLink);
        return new ProductsPage(driver);
    }

    public void clickAbout() {
        click(aboutLink);
    }

    public LoginPage logout() {
        click(logoutLink);
        return new LoginPage(driver);
    }

    public void resetAppState() {
        click(resetAppStateLink);
    }
}