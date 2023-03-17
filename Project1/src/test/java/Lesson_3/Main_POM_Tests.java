package Lesson_3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main_POM_Tests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://www.saucedemo.com/";

    public Main_POM_Tests(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    // Locators using Page Factory
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "product_sort_container")
    private WebElement productSortDropdown;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public void loginWithValidCredentials() {
        driver.get(baseUrl);
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    public void loginWithInvalidCredentials() {
        driver.get(baseUrl);
        usernameInput.sendKeys("invalid_user");
        passwordInput.sendKeys("invalid_password");
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void addItemToCart() {
        driver.get(baseUrl);
        Select sortDropdown = new Select(productSortDropdown);
        sortDropdown.selectByValue("lohi");
        addToCartButtons.get(0).click();
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
    }

    public void removeItemFromCart() {
        addItemToCart();
        cartButton.click();
        removeButtons.get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe((By) removeButtons, 0));
    }

    public void sortItemsByPrice() {
        driver.get(baseUrl);
        Select sortDropdown = new Select(productSortDropdown);
        sortDropdown.selectByValue("lohi");
        boolean isSorted = true;
        for (int i = 0; i < productPrices.size() - 1; i++) {
            double price1 = Double.parseDouble(productPrices.get(i).getText().substring(1));
            double price2 = Double.parseDouble(productPrices.get(i + 1).getText().substring(1));
            if (price1 > price2) {
                isSorted = false;
                break;
            }
        }
        assert isSorted : "Product prices are not sorted in ascending order";
    }

    public void testLoginWithValidCredentials() {
        loginWithValidCredentials();
        assert driver.getCurrentUrl().contains("inventory.html") : "Login with valid credentials failed";
    }

    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.loginWithInvalidCredentials("invalid_user", "invalid_password");
        assert loginPage.isErrorMessageDisplayed() : "Error message not displayed for login with invalid credentials";
    }

}
