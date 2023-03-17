package Lesson_4;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavascriptTestNG {

    static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testJavascript() throws InterruptedException {
        driver.get(BASE_URL);
        Thread.sleep(2000);

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        //set firstName
        executor.executeAsyncScript("document.getElementById('firstName').value='Anatolie';" +
                "var call = arguments[arguments.length - 1];" +
                "window.setTimeout(call, 1500);");

        //perform scroll
        executor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
