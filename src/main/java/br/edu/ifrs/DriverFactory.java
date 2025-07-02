package br.edu.ifrs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "/src/main/resources/webdriver/geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);

            FirefoxOptions options = new FirefoxOptions();

            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
