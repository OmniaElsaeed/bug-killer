
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class CartPageTest {
    WebDriver driver;
    LoginPage loginPage;
    product_page productsPage;
    CartPage cartPage;
    PageBase pageBase;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        productsPage = new product_page(driver);
        loginPage.login("standard_user", "secret_sauce");
        cartPage = new CartPage(driver);
        pageBase = new PageBase(driver);
    }

    // 1-Check Product Details
    @Test
    public void CheckNameAndPriceInCart() {
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        cartPage.WaitcartPageToBeVisible();
        Assert.assertTrue(cartPage.verifyProductInCart("Sauce Labs Backpack", "$29.99"), "Product details are incorrect");
    }

    // 2-Verify cart keeps product after navigation
    @Test
    public void CartKeepsElement() {
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        cartPage.WaitcartPageToBeVisible();

        driver.navigate().back();
        driver.navigate().forward();


        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_contents_container")));

        String expectedName = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.getProductNameInCart(), expectedName, "Product should remain in cart after back and forward navigation");
    }







    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
