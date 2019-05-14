package ru.kontur.test.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

public class LoginPage extends BasePage {

    @FindBy(id = "Email")
    private WebElement inputEmail;

    @FindBy(id = "Password")
    private WebElement inputPassword;

    @FindBy(id = "LoginButton")
    private WebElement buttonLogin;


    public LoginPage(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        super(aDriver, aWait, aTimeOuts);
    }

    public void login(String aEmail, String aPassword) {
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(aEmail);
        inputPassword.clear();
        inputPassword.sendKeys(aPassword);
        buttonLogin.click();
    }
}
