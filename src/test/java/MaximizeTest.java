
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

public class MaximizeTest {
    private WebDriver driver = null;
    private String otusUrl = "https://otus.ru";

    private By emailLocator = By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']/div[@class='sc-1ij08sq-0 fQxsKJ sc-rq8xzv-2 xkNdd']");
    private By passwordLocator = By.xpath("//div[@class='sc-177u1yy-0 sc-rq8xzv-2 xkNdd']");
    private By typeInEmailLocator = By.xpath("//input[@name='email']");
    private By typeInPasswordLocator = By.xpath("//input[@type='password']");
    private By singInButtonLocator = By.xpath("//button[@class='sc-9a4spb-0 eQlGvH sc-11ptd2v-2-Component cElCrZ']");
    private By logInButtonLocator = By.xpath("//button[@class = 'sc-mrx253-0 enxKCy sc-945rct-0 iOoJwQ']");

    @BeforeEach
    public void initDriver() {
        String argument = "--start-maximized";
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
    public void otuslogIn() {
        driver.get(otusUrl);
        getElement(logInButtonLocator).click();

        getElement(emailLocator).click();
        getElement(typeInEmailLocator).sendKeys("test9@gmail.com");

        getElement(passwordLocator).click();
        getElement(typeInPasswordLocator).sendKeys("vg2hsgL–j7!");

        getElement(singInButtonLocator).click();

        String cookies = String.valueOf(driver.manage().getCookies());
        Assertions.assertTrue(!cookies.isEmpty());
        System.out.println(cookies);
    }

}
