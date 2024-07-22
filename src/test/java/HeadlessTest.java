import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeadlessTest {
    private WebDriver driver = null;
    private String duckUrl = "https://duckduckgo.com/";

    private By inputSearchLocator = By.xpath("//input[@aria-label]");
    private By searchButtonLocator = By.xpath("//button[@aria-label='Search']");
    private By firstArticleLocator = By.xpath("//article/div[2]/h2/a/span");

    @BeforeEach
    public void initDriver() {
        String argument = "headless";
        driver = new WebDriverFactory().create(argument);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }

    private WebElement getElement(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void searchOtus() {
        driver.get(duckUrl);
        getElement(inputSearchLocator).sendKeys("ОТУС");
        getElement(searchButtonLocator).click();
        String firstResult = getElement(firstArticleLocator).getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", firstResult);
    }

}
