
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
   //1-Enter With Valid Data
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        String actualUrl = loginPage.findCurrentURL();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed or did not redirect correctly.");
    }
//2-Enter with Invalid Data
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("incorrect_user", "wrong_password");

        String errorMessage = loginPage.getText(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(errorMessage, expectedMessage, "Error message not displayed correctly.");
    }
    //3-Miss usernameAndpassword
    @Test
    public void testLoginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        String errorMessage = loginPage.getText(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Username is required";

        Assert.assertEquals(errorMessage, expectedMessage, "Error message not displayed correctly for empty fields.");
    }
    /*4-Miss password field*/
    @Test
    public void testLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        String errorMessage = loginPage.getText(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Password is required";

        Assert.assertEquals(errorMessage, expectedMessage, "Error message not displayed correctly for missing password.");
    }
/*5-miss username field*/
@Test
public void testLoginWithoutUsername() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("", "secret_sauce");

    String errorMessage = loginPage.getText(By.cssSelector("h3[data-test='error']"));
    String expectedMessage = "Epic sadface: Username is required";

    Assert.assertEquals(errorMessage, expectedMessage, "Error message not displayed correctly for missing username.");
}
/*6-Password is hidden*/
    @Test
    public void testPasswordIsMasked() {
        LoginPage loginPage = new LoginPage(driver);
        String inputType = loginPage.getPasswordInputType();
        Assert.assertEquals(inputType, "password", "Password field is not masked.");
    }
    /*7-login with enter key instead of button*/
    @Test
    public void testLoginWithEnterKey() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithEnterKey("standard_user", "secret_sauce");

        String actualUrl = loginPage.findCurrentURL();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualUrl, expectedUrl, "Login with Enter key failed.");
    }





    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
