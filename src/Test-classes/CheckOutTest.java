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
    PageBase pageBase;
    WebDriverWait wait;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        productsPage = new product_page(driver);
        cartPage = new CartPage(driver);
        pageBase = new PageBase(driver);
        checkoutPage = new CheckoutPage(driver);

     productsPage.addBackpackToCart();
        cartPage.OpenCart();
        cartPage.clickCheckoutButton();
    }

    @Test
    public void fillCheckoutInformation() {
        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement postalCode = driver.findElement(By.id("postal-code"));

        firstName.sendKeys("omnia");
        lastName.sendKeys("elsaeed");
        postalCode.sendKeys("123547");
    }

    @Test
    public void CheckValidInputs() {
        fillCheckoutInformation();
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two"), "Should be on checkout step two");
    }

    @Test
    public void CheckEmptyFields() {
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        By errorLocator = By.cssSelector("h3[data-test='error']");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        String actualError = error.getText();
        Assert.assertEquals(actualError, "Error: First Name is required");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
