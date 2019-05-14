package ru.kontur.test.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

/**
 * Class for working with selenium screenshot feature
 */
public class ScreenShoter {

    private static final String SCREENSHOT_NAME_FORMAT = "%s.%s";
    private static final String SCREENSHOT_NAME_EXTENSION = "png";

    private WebDriver driver;
    private String mScreenshotsFolderPath;


    /**
     * Constructor initializes the variables and creates a screenshot folder
     * @param aDriver driver
     * @param aPath path to the screenshot folder
     */
    public ScreenShoter(@Nonnull WebDriver aDriver, @Nonnull String aPath) {
        driver = aDriver;
        mScreenshotsFolderPath = aPath;
        File screenshotsFolder = new File(mScreenshotsFolderPath);
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdir();
        }
    }

    /**
     * Creates screenshot
     * @param aTestName name of the test after which the screenshot is taken
     */
    public void makeScreenshot(@Nonnull String aTestName) {
        String fileName = String.format(SCREENSHOT_NAME_FORMAT, aTestName, SCREENSHOT_NAME_EXTENSION);
        try {
            File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFiler, new File(mScreenshotsFolderPath + "\\" + fileName));
        } catch (IOException ex) {
            System.out.println("Error: makeScreenshot - " + ex);
        }
    }
}
