import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Запускаем браузер, открывае стр. http://www.google.com
 * Выполняем поиск по слову "webdriver"
 */
public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        driver = new ChromeDriver(options);

 /*       InternetExplorerOptions options = new InternetExplorerOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.destructivelyEnsureCleanSession();//doesn't work
        driver = new InternetExplorerDriver(options);
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("cookies is: " + cookies);
*/
/*        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe")));
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("start-fullscreen");
        driver = new FirefoxDriver(options);*/

        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://www.google.com");
        ((JavascriptExecutor)driver).executeScript("alert(\"HI\");");
        sleep(3000);
        driver.findElement(By.name("q")).sendKeys("webdriver", Keys.ENTER);
        wait.until(titleIs("webdriver - Поиск в Google"));
        System.out.println("============================================");
        System.out.println("cookies is: " + driver.manage().getCookies());
        System.out.println("============================================");
        driver.manage().deleteAllCookies();
        System.out.println("cookies is: " + driver.manage().getCookies());
    }

    /**
     * don't work for IE
     * Unable to find element with css selector == span.ly0Ckb
     */
    @Test
    public void screenKeyboardTest(){
        driver.get("http://www.google.com");
        //open screen keyboard
        driver.findElement(By.cssSelector("span.ly0Ckb")).click();
        driver.findElement(By.id("K32")).click(); //add space
        //close screen keyboard
        driver.findElement(By.cssSelector("span.ly0Ckb")).click();
        //driver.findElement(By.xpath("//div[@class='vk-t-btn vk-sf-cl']")).click();
        driver.findElement(By.name("q")).sendKeys("webdriver", Keys.ENTER);
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    /**
     * сценарий для логина в панель администрирования
      */
    @Test
    public void litecartAdmit(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
