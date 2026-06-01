package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupSmokeTest extends BaseTest {

    @Test
    public void shouldOpenSauceDemoLoginPage() {
        String title = this.driver.getTitle();
        Assert.assertEquals(title, "Swag Labs");
    }


}
