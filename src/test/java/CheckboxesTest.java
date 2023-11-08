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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckboxesTest {
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
    public void checkboxes() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[text()= 'Checkboxes']")).click();
        List<WebElement> checkbox = driver.findElements(By.cssSelector("[type=checkbox]"));

        Assert.assertFalse(checkbox.get(0).isSelected());

        checkbox.get(0).click();

        Assert.assertTrue(checkbox.get(0).isSelected());
        Assert.assertTrue(checkbox.get(1).isSelected());

        checkbox.get(1).click();

        Assert.assertFalse(checkbox.get(1).isSelected());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

