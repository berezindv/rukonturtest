package ru.kontur.test.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

public class MainMenu extends BasePage {

    @FindBy(id = "MainMenu_Payments_LinkText")
    private WebElement menuPayments;

    @FindBy(id = "MainMenu_Contractors_LinkText")
    private WebElement menuContractors;


    public MainMenu(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        super(aDriver, aWait, aTimeOuts);
    }

    public void openPaymentsPage() {
        try {
            menuPayments.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("MainMenu_Payments_LinkText")).click();
        }
    }

    public void openContractorsPage() {
        try {
            menuContractors.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("MainMenu_Contractors_LinkText")).click();
        }
    }
}
