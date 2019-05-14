package ru.kontur.test.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    TimeOuts timeOuts;


    BasePage(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        driver = aDriver;
        wait = aWait;
        timeOuts = aTimeOuts;
        PageFactory.initElements(aDriver, this);
    }
}
