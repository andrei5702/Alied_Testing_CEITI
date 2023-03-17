package Lesson_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class DockerTTestNG {
    static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    //static final String BASE_URL = "https://www.saucedemo.com/";
    static private WebDriver driver;
//    static WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc().enableRecording();

    @BeforeClass
    static public void beforeClass1() {
        //System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");
        //driver = new ChromeDriver();
//        driver = wdm.create();
        driver.manage().window().maximize();
    }

    @Test
    public void helloWardTest() throws InterruptedException {
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
        firstName.clear();
        firstName.sendKeys("Anatolie");

        WebElement lastName = driver.findElement(By.xpath("//*[@id='lastName']"));
        lastName.clear();
        lastName.sendKeys("Snegur");

        WebElement userEmail = driver.findElement(By.xpath("//*[@id='userEmail']"));
        userEmail.clear();
        userEmail.sendKeys("bbs@mail.ru");

        driver.findElement(By.xpath("//*[@id='genterWrapper']//*[@type='radio' and @value='Male']"))
                .click();

        WebElement mobile = driver.findElement(By.xpath("//*[@id='userNumber']"));
        mobile.clear();
        mobile.sendKeys("0671111187");

        driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"))
                .click();
        driver.findElement(By.cssSelector(".react-datepicker__year-select"))
                .click();
        driver.findElement(By.cssSelector(".react-datepicker__year-select option[value='1975']"))
                .click();

        System.out.println("End");
    }

    @AfterClass
    static  public void tearDown() {
        driver.quit();
    }
}
