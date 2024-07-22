package factory;

import exceptions.BrowserNotSupportedExecption;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser");

    public WebDriver create(String... argument) {
        switch (browserName) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(argument);
                return new ChromeDriver(chromeOptions);
            }
            default: {
                throw new BrowserNotSupportedExecption(browserName);
            }
        }
    }
}