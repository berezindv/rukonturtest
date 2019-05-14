package ru.kontur.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;
import ru.kontur.test.pages.pages.*;

/**
 * Page factory
 */
public class Elba {

    private WebDriver driver;
    private WebDriverWait wait;
    private TimeOuts timeOuts;


    public Elba(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        driver = aDriver;
        wait = aWait;
        timeOuts = aTimeOuts;
    }

    public LoginPage loginPage() { return new LoginPage(driver, wait, timeOuts);}

    public ManageOrganizationsPage manageOrganizationsPage() { return new ManageOrganizationsPage(driver, wait, timeOuts);}

    public MainMenu mainMenu() { return new MainMenu(driver, wait, timeOuts);}

    public PaymentsPage paymentsPage() { return new PaymentsPage(driver, wait, timeOuts);}

    public ContractorsPage contractorsPage() { return new ContractorsPage(driver, wait, timeOuts);}
}
