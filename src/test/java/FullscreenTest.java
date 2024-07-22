import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class FullscreenTest {
    private WebDriver driver = null;
    private Waiter waiter = null;
    private String photoFlashUrl = "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818";

    private By containerLocator = By.xpath("//div[@class = 'pp_hoverContainer']");
    private By clickableLocator = By.xpath("//a[@href='assets/images/p1.jpg']/div[@class='content-overlay']");

    @BeforeEach
    public void initDriver() {
        String argument = "--start-fullscreen";
        driver = new WebDriverFactory().create(argument);
        waiter = new Waiter(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void containerIsVisible() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        driver.get(photoFlashUrl);

        Assertions.assertTrue(waiter.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(containerLocator)));

        new Actions(driver).
                moveToElement(driver.findElement(clickableLocator))
                .click()
                .perform();

        Assertions.assertTrue(waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(containerLocator)));
    }
}
