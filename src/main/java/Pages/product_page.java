package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class product_page extends PageBase {


    private By pageTitle = By.cssSelector(".title");  // CSS Selector للعنوان
    private By inventoryContainer = By.id("inventory_container"); // ID للحاوية الرئيسية
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack"); // ID للزر الأول
    private By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light"); // ID للزر الثاني
    private By removeBackpack = By.id("remove-sauce-labs-backpack"); // ID لزر الإزالة
    private By cartIcon = By.cssSelector(".shopping_cart_link");  // CSS Selector لأيقونة السلة
    private By cartBadge = By.cssSelector(".shopping_cart_badge"); // CSS Selector للعداد
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By addFleeceJacketToCartButton = By.id("add-to-cart-sauce-labs-fleece-jacket");
    private By addOnesieToCartButton = By.id("add-to-cart-sauce-labs-onesie");

    public product_page(WebDriver driver) {
        super(driver);
    }
    public void addOnesieToCart() {
        this.clickOnElement(this.addOnesieToCartButton);
    }

    public int getProductCount() {
        return driver.findElements(By.cssSelector(".inventory_item")).size(); // تحديد جميع المنتجات
    }


    public void addToCartByIndex(int index) {
        driver.findElements(By.cssSelector(".btn_inventory")).get(index).click(); // جميع الأزرار التي تضيف إلى السلة
    }


    public void removeBackpackFromCart() {
        driver.findElement(removeBackpack).click();
    }


    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }
    public void waitCartBadge() {
       waitForElementVisibility(cartBadge);
    }


    public String getProductTitle() {
        return driver.findElement(pageTitle).getText();
    }


    public void addBackpackToCart() {
        waitForElementToBeClickable(addToCartBackpack);
        driver.findElement(addToCartBackpack).click();
    }
    public void addBacklightToCart() {
        waitForElementToBeClickable(addToCartBikeLight);
        driver.findElement(addToCartBikeLight).click();
    }
    public void addFleeceJacketToCart() {
        this.clickOnElement(this.addFleeceJacketToCartButton);
    }
    public void OpenCart() {
        this.clickOnElement(By.className("shopping_cart_link"));
    }


    public void addBikeLightToCart() {
        driver.findElement(addToCartBikeLight).click();
    }
    public void openCartIcon() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    public String getCartItemCount() {
        return getText(cartIcon);
    }


    public boolean isInventoryDisplayed() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }
    public boolean isCartBadgeCountCorrect(int expectedCount) {
        this.waitForElementVisibility(this.cartBadge);
        String count = this.driver.findElement(this.cartBadge).getText();
        return Integer.parseInt(count) == expectedCount;
    }
}
