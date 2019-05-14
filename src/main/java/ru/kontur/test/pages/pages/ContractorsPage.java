package ru.kontur.test.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

public class ContractorsPage extends BasePage {

    @FindBy(id = "RemoveContractorLink")
    private WebElement linkRemoveContractor;

    @FindBy(id = "DeleteContractorLightbox_AcceptButton")
    private WebElement buttonAcceptDeletion;


    public ContractorsPage(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        super(aDriver, aWait, aTimeOuts);
    }

    public void removeContractor(String aContractor) {
        WebElement linkContractor = driver.findElement(By.xpath("//a[contains(text(), '" + aContractor + "')]"));
        linkContractor.click();
        linkRemoveContractor.click();
        buttonAcceptDeletion.click();
    }
}
