import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest(){
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

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
