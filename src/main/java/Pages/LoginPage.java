package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {


    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String username, String password) {
        fillElement(usernameField, username);
        fillElement(passwordField, password);
        clickOnElement(loginButton);
    }
}
