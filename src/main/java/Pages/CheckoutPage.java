package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends PageBase {


    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");
    private By errorMessage = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3");
    private By checkoutButton = By.id("checkout");
    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.className("inventory_item_price");
    private By checkoutpagelocator=By.id("page_wrapper");
    private By checkoutlocatorpage2=By.id("root");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public void clickCheckout() {
        waitForElementToBeClickable(checkoutButton);
        clickOnElement(checkoutButton);
    }

    public void fillCheckoutInformation() {
        driver.findElement(firstNameField).sendKeys("omnia");
        driver.findElement(lastNameField).sendKeys("elsaeed");
        driver.findElement(zipCodeField).sendKeys("123547");
    }
    public void Waitthecheckoutpage()
    {
        waitForElementVisibility(checkoutpagelocator);
    }
    public void checkoutpagetwo()
    {
        waitForElementVisibility(checkoutlocatorpage2);
    }

    public void clickContinue() {
        waitForElementToBeClickable(continueButton);
        clickOnElement(continueButton);
    }


    public void clickCancel() {
        waitForElementToBeClickable(cancelButton);
        clickOnElement(cancelButton);
    }


    public String geterrormessage()
    {
        waitForElementVisibility(errorMessage);
        return driver.findElement(errorMessage).getText();
    }
    public void enterFirstName(String firstName) {
        By firstNameLocator = By.id("first-name");
        driver.findElement(firstNameLocator).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        By lastNameLocator = By.id("last-name");
        driver.findElement(lastNameLocator).sendKeys(lastName);
    }
    public void enterZipCode(String zipCode) {
        By zipCodeLocator = By.id("postal-code");
        driver.findElement(zipCodeLocator).sendKeys(zipCode);
    }
    public String getProductNameInCheckout() {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPriceInCheckout() {
        return driver.findElement(By.className("inventory_item_price")).getText();
    }
    public void clickFinish() {

        waitForElementToBeClickable(By.id("finish"));


        clickOnElement(By.id("finish"));
    }



}
