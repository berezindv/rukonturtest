package ru.kontur.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.kontur.test.pages.Elba;
import ru.kontur.test.utils.DriverStarter;
import ru.kontur.test.utils.PropertiesReader;
import ru.kontur.test.utils.ScreenShoter;
import ru.kontur.test.utils.TimeOuts;


public class BaseTest {

    private static final String CONFIG_PATH = "src/test/resources/.properties";
    protected static ScreenShoter screenShoter;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static TimeOuts timeOuts;
    protected static Elba elba;

    /**
     * This method runs before any other method.
     */
    @BeforeSuite
    public static void setUp() {
        PropertiesReader config = loadConfig();
        DriverStarter driverStarter = new DriverStarter(config);
        driver = driverStarter.driver;
        wait = driverStarter.wait;
        timeOuts = driverStarter.timeOuts;
        elba = new Elba(driver, wait, timeOuts);
        screenShoter = new ScreenShoter(driver, config.getProperty("screenshotsFolderPath"));
    }

    /**
     * This method reads the .properties file
     * @return config as PropertiesReader object
     */
    private static PropertiesReader loadConfig() {
        PropertiesReader config = PropertiesReader.getInstance();
        Assert.assertTrue(config.readFromFile(CONFIG_PATH), "Can't read config file");
        return config;
    }

    /**
     * Always remember to quit
     */
    @AfterSuite
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
