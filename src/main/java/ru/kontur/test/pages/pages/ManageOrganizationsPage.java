package ru.kontur.test.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

import static ru.kontur.test.TestData.ORG_NAME;

public class ManageOrganizationsPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), '" + ORG_NAME + "')]")
    private WebElement linkOpenOrganization;


    public ManageOrganizationsPage(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        super(aDriver, aWait, aTimeOuts);
    }

    public void selectOrganization() {
        linkOpenOrganization.click();
    }
}
