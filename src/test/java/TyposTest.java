import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TyposTest {
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
    public void checkSpelling() {

        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[text()= 'Typos']")).click();
        List<String> textExpected = new ArrayList<>();
        textExpected.add("This example demonstrates a typo being introduced. It does it randomly on each page load.");
        textExpected.add("Sometimes you'll see a typo, other times you won't.");

        List<WebElement> textActual = driver.findElements(By.tagName("p"));

        Assert.assertEquals(textActual.get(0).getText(), textExpected.get(0), "Please reload the page");
        Assert.assertEquals(textActual.get(1).getText(), textExpected.get(1), "Please reload the page");

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
