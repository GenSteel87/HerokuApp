import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddRemoveElementsTest {
    WebDriver driver;
    @BeforeMethod
    public  void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void addRemoveElements(){
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[text()= 'Add/Remove Elements']")).click();
        WebElement addElementButton = driver.findElement(By.xpath("//button[text()= 'Add Element']"));
        addElementButton.click();
        addElementButton.click();
        driver.findElement(By.xpath("//button[text()= 'Delete'][2]")).click();

        Assert.assertEquals(driver.findElements(By.xpath("//button[text()= 'Delete']")).size(), 1);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
