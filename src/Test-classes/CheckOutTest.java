import Pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class CheckOutTest {

    WebDriver driver;
    LoginPage loginPage;
    product_page productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        // Pages initialization
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        productsPage = new product_page(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Add item to cart
        productsPage.addBackpackToCart();

        // Navigate to cart
        productsPage.openCartIcon();  // دي لازم تكون موجودة في كلاس الـ product_page وتضغط على أيقونة الكارت

        // Click checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
    }
//1-Check inputs
    @Test
    public void CheckValidInputs() {
        checkoutPage.fillCheckoutInformation();  // تأكد إن الدالة دي بتدخل البيانات صح

        checkoutPage.clickContinue();

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two"), "Should be on checkout step two");
    }
//2-Miss All fields
    @Test
    public void CheckEmptyFields() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        String actualError = checkoutPage.getErrorMessage();
        Assert.assertEquals(actualError, "Error: First Name is required");
    }
/*3-Miss ZIP code*/
    @Test
    public void testCheckoutWithoutZipCode() {
        checkoutPage.enterFirstName("Omnia");
        checkoutPage.enterLastName("Elsaeed");
        checkoutPage.enterZipCode("");  // Leave Zip Code empty
        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: Postal Code is required", "Error message for missing zip code was not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
