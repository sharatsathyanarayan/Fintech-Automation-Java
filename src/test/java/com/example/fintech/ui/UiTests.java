package com.example.fintech.ui;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UiTests extends BaseUiTest {

    @Test
    @Description("User registration flow (happy path)")
    public void userRegistrationFlow() {
        driver.get(uiUrlWithApi());
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("accountType")).sendKeys("premium");
        driver.findElement(By.id("registerBtn")).click();

        WebElement toast = driver.findElement(By.id("toast"));
        Assert.assertTrue(toast.getText().contains("User created"));
    }

    @Test
    @Description("Transaction creation flow (happy path)")
    public void transactionCreationFlow() {
        driver.get(uiUrlWithApi());
        driver.findElement(By.id("userId")).sendKeys("123");
        driver.findElement(By.id("amount")).sendKeys("100.50");
        driver.findElement(By.id("type")).sendKeys("transfer");
        driver.findElement(By.id("recipientId")).sendKeys("456");
        driver.findElement(By.id("txBtn")).click();

        WebElement toast = driver.findElement(By.id("toast"));
        Assert.assertTrue(toast.getText().contains("Transaction created"));
    }

    @Test
    @Description("Validation errors are shown")
    public void validationErrors() {
        driver.get(uiUrlWithApi());
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("registerBtn")).click();
        WebElement toast = driver.findElement(By.id("toast"));
        Assert.assertTrue(toast.getText().toLowerCase().contains("error"));
    }
}
