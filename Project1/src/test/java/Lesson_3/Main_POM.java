package Lesson_3;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main_POM {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Main_POM_Tests tests = new Main_POM_Tests(driver);

        tests.testLoginWithValidCredentials();
        tests.testLoginWithInvalidCredentials();
        tests.addItemToCart();
        tests.removeItemFromCart();
        tests.sortItemsByPrice();

        driver.quit();
    }
}
