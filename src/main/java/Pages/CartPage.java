//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase {
    private By cartIcon = By.className("shopping_cart_link");
    private By cartItemCount = By.className("shopping_cart_badge");
    private By productNameInCart = By.className("inventory_item_name");
    private By productPriceInCart = By.className("inventory_item_price");
    private By removeButton = By.className("cart_button");
    private By productPageLocator = By.id("contents_wrapper");
    private By cartpagelocator = By.id("page_wrapper");
    private By checkoutButton = By.id("checkout");

    private By continueshoppinglocator = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameInCart() {
       return driver.findElement(productNameInCart).getText();
    }

    public String getProductPriceInCart() {
        return driver.findElement(productPriceInCart).getText();
    }

    public boolean verifyProductInCart(String expectedProductName, String expectedProductPrice) {
        return this.getProductNameInCart().equals(expectedProductName) && this.getProductPriceInCart().equals(expectedProductPrice);
    }

    public void removeProductFromCart() {
        this.clickOnElement(this.removeButton);
    }

    public void WaitProductPageToBeVisible() {
        waitForElementVisibility(By.id("inventory_container"));
    }

    public void WaitcartPageToBeVisible() {
        waitForElementVisibility(By.id("cart_contents_container"));
    }


    public void goToCart() {
        this.clickOnElement(this.cartIcon);
    }

    public void clickContinueshopping() {
        waitForElementToBeClickable(continueshoppinglocator);
        clickOnElement(continueshoppinglocator);
    }


    public void OpenCart() {
        this.clickOnElement(By.className("shopping_cart_link"));
    }
    public void clickCheckoutButton() {

        waitForElementToBeClickable(checkoutButton);
        clickOnElement(checkoutButton);
    }
}
