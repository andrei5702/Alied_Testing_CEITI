package Lesson_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoQATextBoxAutomation {

    public static void main(String[] args) {

        // Set the path to the Firefox driver executable
        System.setProperty("webdriver.gecko.driver", "geckodriver");

        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();

        // Navigate to the demoqa.com website
        driver.get("https://demoqa.com/text-box");

        // Find the Full Name text box and enter a value
        driver.findElement(By.id("userName")).sendKeys("John Smith");

        // Find the Email text box and enter a value
        driver.findElement(By.id("userEmail")).sendKeys("john.smith@example.com");

        // Find the Current Address text box and enter a value
        driver.findElement(By.id("currentAddress")).sendKeys("123 Main Street");

        // Find the Permanent Address text box and enter a value
        driver.findElement(By.id("permanentAddress")).sendKeys("456 Oak Avenue");

        // Find the Submit button and click it
        driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();

        // Close the browser
        driver.quit();

    }

}
