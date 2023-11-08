import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void inputValues() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[text()= 'Inputs']")).click();
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.click();
        inputField.sendKeys("TMS");

        Assert.assertEquals(inputField.getText(), "");

        inputField.click();
        inputField.sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(inputField.getText(), "1");
        //TBD
        inputField.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(inputField.getText(), "0");

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
