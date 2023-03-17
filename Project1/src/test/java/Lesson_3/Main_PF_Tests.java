package Lesson_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main_PF_Tests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://www.saucedemo.com/";

    // Locators for login page
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Locators for inventory page
    private By productSortDropdown = By.className("product_sort_container");
    private By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    private By removeButtons = By.xpath("//button[text()='Remove']");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartButton = By.id("shopping_cart_container");

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void loginWithValidCredentials() {
        driver.get(baseUrl);
        driver.findElement(usernameInput).sendKeys("standard_user");
        driver.findElement(passwordInput).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    public void loginWithInvalidCredentials() {
        driver.get(baseUrl);
        driver.findElement(usernameInput).sendKeys("invalid_user");
        driver.findElement(passwordInput).sendKeys("invalid_password");
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    public void addItemToCart() {
        driver.get(baseUrl);
        Select sortDropdown = new Select(driver.findElement(productSortDropdown));
        sortDropdown.selectByValue("lohi");
        List<WebElement> addButtons = driver.findElements(addToCartButtons);
        addButtons.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
    }

//    public void removeItemFromCart() {
//        addItemToCart();
//        driver.findElement(cartButton).click();
//        List<WebElement> removeButtons = driver.findElements(removeButtons);
//        removeButtons.get(0).click();
//        wait.until(ExpectedConditions.numberOfElementsToBe((By) removeButtons, 0));
//    }

    public void sortItemsByPrice() {
        driver.get(baseUrl);
        Select sortDropdown = new Select(driver.findElement(productSortDropdown));
        sortDropdown.selectByValue("lohi");
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
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
}
