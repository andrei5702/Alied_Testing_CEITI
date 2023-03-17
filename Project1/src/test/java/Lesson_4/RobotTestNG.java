package Lesson_4;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RobotTestNG {

    private WebDriver driver;

    @BeforeClass
    public void setUpProperty() {
        System.out.println("setUpProperty");
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void robotKeyboardTest() throws IOException, InterruptedException, AWTException {

        Runtime run = Runtime.getRuntime();
        driver.get("https://google.com");
        //driver.get("https://demoqa.com/automation-practice-form");
        String command = "notepad.exe";

        //run.exec(command); // Open notepad
        Thread.sleep(1000);

        Robot robot = new Robot();

        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_W);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_L);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_M);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_L);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyPress(KeyEvent.VK_ENTER);
        System.out.println("Stop robotKeyboardTest");

    }

    @Test
    public void mouseEventTest() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        int k = 30;
        int x=0, y =0;
        for (int i=0; i<k; i++ ) {
            x -= 10;
            y += 10;
            robot.mouseMove(x, y);
            Thread.sleep(100);
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("tearDown");
        driver.quit();
    }
}