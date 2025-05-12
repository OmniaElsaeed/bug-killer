package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageBase {
    WebDriver driver;
    public static Duration WAIT = Duration.ofSeconds(90);

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForUrlToContain(String urlPart, int... timeoutInSeconds) {
        int timeout = timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : 10;}

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void fillElement(By locator, String value) {
        this.waitForElementVisibility(locator);
        this.driver.findElement(locator).sendKeys(value);
    }


    public void clickOnElement(By locator) {
        this.waitForElementVisibility(locator);
        this.waitForElementToBeClickable(locator);
        this.driver.findElement(locator).click();
    }
    public void waitForUrlToContain(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.urlContains(keyword));
    }

    public String getText(By locator) {
        this.waitForElementVisibility(locator);
        return this.driver.findElement(locator).getText();
    }


    public String findCurrentURL() {
        return this.driver.getCurrentUrl();
    }
}
