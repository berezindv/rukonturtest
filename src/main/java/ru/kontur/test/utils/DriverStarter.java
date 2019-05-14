package ru.kontur.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverStarter {

    public WebDriver driver;
    public WebDriverWait wait;
    public TimeOuts timeOuts;

    /**
     * Start and configure WebDriver
     * @param aConfig config file as PropertiesReader object
     */
    public DriverStarter(@Nonnull PropertiesReader aConfig) {

        timeOuts = new TimeOuts(aConfig);

        File file = new File(aConfig.getProperty("chromeDriverPath"));
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(timeOuts.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeOuts.pageLoadTimeout, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, timeOuts.webDriverWait, timeOuts.sleepInMillis);
    }
}
