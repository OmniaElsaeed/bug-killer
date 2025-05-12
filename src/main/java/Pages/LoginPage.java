package Pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {


    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
    public String getPasswordInputType() {
        return driver.findElement(By.id("password")).getAttribute("type");
    }
    public void loginWithEnterKey(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password + Keys.ENTER);
    }

    public void login(String username, String password) {
        fillElement(usernameField, username);
        fillElement(passwordField, password);
        clickOnElement(loginButton);
    }
}
