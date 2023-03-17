package Lesson_2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertEquals;

public class HelloWorld {

    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testHelloWorld() throws InterruptedException {
        System.out.println("Start helloWardTest");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys("Anatolie");


        assertEquals("Anatolie", firstName.getAttribute("value"));
        System.out.println("Finish helloWardTest");
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println("Start test1");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys("Andrei");


        assertEquals("Andrei", firstName.getAttribute("value"));
        System.out.println("Finish test1");
    }

    @Test
    public void test2() throws InterruptedException {
        System.out.println("Start test2");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys("Doroganici");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.clear();
        lastName.sendKeys("Andrei");


        assertEquals("Doroganici", firstName.getAttribute("value"));
        assertEquals("Andrei", lastName.getAttribute("value"));
        System.out.println("Finish test2");
    }


    @Test
    public void test3() throws InterruptedException {
        System.out.println("Start test3");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.clear();
        currentAddress.sendKeys("Lorem Ipsum");

        assertEquals("Lorem Ipsum", currentAddress.getAttribute("value"));
        System.out.println("Finish test3");
    }

    @Test
    public void test4() throws InterruptedException {
        System.out.println("Start test4");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement userNumber = driver.findElement(By.id("userNumber"));
        userNumber.clear();
        userNumber.sendKeys("1234567890");

        assertEquals("Andrei", userNumber.getAttribute("value"));
        System.out.println("Finish test4");
    }

    @Test
    public void test5() throws InterruptedException {
        System.out.println("Start test5");
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.clear();
        userEmail.sendKeys("test@gmail.com");


        assertEquals("test@gmail.com", userEmail.getAttribute("value"));
        System.out.println("Finish test5");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
