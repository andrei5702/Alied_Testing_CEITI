package Lesson_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HelloWorldTestNG {
    static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    //static final String BASE_URL = "https://www.saucedemo.com/";
    static private WebDriver driver;

    @BeforeClass
    static public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void helloWorldTest() throws InterruptedException {
        System.out.println("Start helloWorldTest");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
        firstName.clear();
        firstName.sendKeys("Hello World");

        System.out.println("Finish helloWorldTest");
    }

    @AfterClass
    static  public void tearDown() {
        driver.quit();
    }
}

