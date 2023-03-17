package Lesson_4;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicTestNG {
    public static Logger logger = Logger.getLogger(BasicTest.class);

    public static ConcurrentHashMap<String, WebDriver> chm = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Long, WebDriver> tid = new ConcurrentHashMap<>();
    protected static final String USER_NAME = "anatolie";
    protected static final String USER_PASSWORD = "Demoqa$123";

    static public WebDriver driverStatic;
    protected WebDriver driver;

    @BeforeTest
    protected WebDriver setUp() {
        WebDriver localDriver = WebDriverManager.chromedriver().create();
        driver = localDriver;
        chm.put(this.getClass().getSimpleName(), driver);
        tid.put(Thread.currentThread().getId(), driver);
        return localDriver;
    }

    @AfterTest
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    protected WebDriver setLocalDriverTest() {
        WebDriver localDriver = setUp();
        driverStatic = localDriver;
        return localDriver;
    }

    @Test
    protected WebDriver setRemoteDriverTest() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "106.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "enableLog", true
        ));
        RemoteWebDriver remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(
                    URI.create("http://192.168.16.104:4444/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = remoteDriver;
        chm.put(this.getClass().getSimpleName(), driver);
        chm.put(String.valueOf(Thread.currentThread().getId()), driver);
        driverStatic = remoteDriver;
        tid.put(Thread.currentThread().getId(), remoteDriver);
        return remoteDriver;
    }
}
