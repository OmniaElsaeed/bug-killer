

import Pages.LoginPage;
import Pages.PageBase;
import Pages.product_page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTests {

    WebDriver driver;
    @BeforeMethod
    public void setup() {
        // تهيئة الـ WebDriver قبل كل اختبار
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        PageBase pageBase=new PageBase(driver);
        pageBase.waitForElementVisibility(By.className("title"));


    }
/*1-Test the Title*/
@Test
public void testProductPageTitle() {
    product_page productPage = new product_page(driver);
    String actualTitle = productPage.getProductTitle();
    System.out.println("Actual Title: " + actualTitle);
    Assert.assertEquals(actualTitle, "Products", "Product page title does not match.");
}
    /*2- Test product List */
    @Test
    public void testProductListDisplayed() {
        product_page productPage = new product_page(driver);
        int productCount = productPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products are displayed");
        System.out.println("Number of products displayed: " + productCount);
    }
    /* 3-Add Product to cart */
    @Test
    public void testAddProductToCart() {
        product_page productPage = new product_page(driver);

        productPage.addToCartByIndex(0);
        productPage.waitCartBadge();
        String cartBadgeCount = productPage.getCartBadgeCount();
        Assert.assertEquals(cartBadgeCount, "1", "Cart count did not update after adding product");

        System.out.println("Product added to cart successfully. Cart count: " + cartBadgeCount);
    }
/*4-Test remove from cart */
    @Test
    public void RemoveProduct()
    {
        product_page productPage = new product_page(driver);
        PageBase pageBase=new PageBase(driver);
        productPage.addBackpackToCart();
        productPage.addBacklightToCart();
        productPage.removeBackpackFromCart();
        By cartIconLocator = By.id("shopping_cart_container");
        pageBase.waitForElementVisibility(cartIconLocator);
         String actual=productPage.getCartBadgeCount();
        Assert.assertEquals(actual,"1","Remove is done ");

    }



    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
