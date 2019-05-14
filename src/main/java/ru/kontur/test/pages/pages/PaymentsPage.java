package ru.kontur.test.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kontur.test.utils.TimeOuts;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PaymentsPage extends BasePage {

    @FindBy(id = "PaymentUpload_PaymentFileUpload_Input")
    private WebElement uploadFile;

    @FindBy(css = ".paymentsList-table-row_imported")
    private WebElement importedPayment;

    @FindBy(id = "ImportFailuresMessage_ReasonsLink")
    private WebElement linkFailureReason;

    @FindBy(css = "div#Lightboxes div#ComponentsHost_FailuresLightbox_Container")
    private WebElement lightboxFailureReason;

    @FindBy(id = "ComponentsHost_FailuresLightbox_AcceptButton")
    private WebElement buttonCloseFailureReason;

    @FindBy(id = "ImportFailuresMessage_Message_CloseLink")
    private WebElement buttonCloseFailureMessage;

    @FindBy(css = "div.c-lightbox-overlay")
    private WebElement lightboxOverlay;

    @FindBy(css = "div#Lightboxes div#ComponentsHost_PaymentEditLightbox_Container")
    private WebElement paymentEditLightbox;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_CloseLink")
    private WebElement paymentEditLightboxCloseButton;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_OperationType_Caption")
    private WebElement paymentEditLightboxOperationType;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_IncomeSum")
    private WebElement paymentEditLightboxIncomeSum;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_TaxSum")
    private WebElement paymentEditLightboxTaxSum;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_Date")
    private WebElement paymentEditLightboxDate;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_Contractor_ContractorName")
    private WebElement paymentEditLightboxContractorName;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_DocumentNumber")
    private WebElement paymentEditLightboxDocumentNumber;

    @FindBy(id = "ComponentsHost_PaymentEditLightbox_PaymentDescription")
    private WebElement paymentEditLightboxPaymentDescription;


    public PaymentsPage(WebDriver aDriver, WebDriverWait aWait, TimeOuts aTimeOuts) {
        super(aDriver, aWait, aTimeOuts);
    }

    public void uploadPaymentFile(String aFilePath) {
        File file = new File(aFilePath);
        try {
            uploadFile.sendKeys(file.getAbsolutePath());
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("PaymentUpload_PaymentFileUpload_Input")).sendKeys(file.getAbsolutePath());
        }
    }

    public void seeFailureReason() {
        try {
            linkFailureReason.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("ImportFailuresMessage_ReasonsLink")).click();
        }
        wait.until(ExpectedConditions.visibilityOf(lightboxFailureReason));
    }

    public void closeFailureReason() {
        buttonCloseFailureReason.click();
        wait.until(ExpectedConditions.invisibilityOf(lightboxOverlay));
    }

    public boolean reasonIsPresent() {
        driver.manage().timeouts().implicitlyWait(timeOuts.implicitlyWaitMin, TimeUnit.SECONDS);
        boolean failureReasonPresent = driver.findElements(By.cssSelector("div#Lightboxes div#ComponentsHost_FailuresLightbox_Container")).size() > 0;
        driver.manage().timeouts().implicitlyWait(timeOuts.implicitlyWait, TimeUnit.SECONDS);
        return failureReasonPresent;
    }

    public void closeFailureMessage() {
        buttonCloseFailureMessage.click();
    }

    public void openEditPaymentLightBox() {
        importedPayment.click();
        wait.until(ExpectedConditions.visibilityOf(paymentEditLightbox));
    }

    public void closeEditPaymentLightBox() {
        paymentEditLightboxCloseButton.click();
    }

    public boolean editPaymentIsPresent() {
        driver.manage().timeouts().implicitlyWait(timeOuts.implicitlyWaitMin, TimeUnit.SECONDS);
        boolean editPaymentPresent = driver.findElements(By.cssSelector("div#Lightboxes div#ComponentsHost_PaymentEditLightbox_Container")).size() > 0;
        driver.manage().timeouts().implicitlyWait(timeOuts.implicitlyWait, TimeUnit.SECONDS);
        return editPaymentPresent;
    }

    public String getEditPaymentOperationTypeValue() {
        return paymentEditLightboxOperationType.getText();
    }

    public String getEditPaymentIncomeSumValue() {
        return paymentEditLightboxIncomeSum.getAttribute("value");
    }

    public String getEditPaymentTaxSumValue() {
        return paymentEditLightboxTaxSum.getAttribute("value");
    }

    public String getEditPaymentDateValue() {
        return paymentEditLightboxDate.getAttribute("value");
    }

    public String getEditPaymentContractorNameValue() {
        return paymentEditLightboxContractorName.getAttribute("value");
    }

    public String getEditPaymentDocumentNumberValue() {
        return paymentEditLightboxDocumentNumber.getAttribute("value");
    }

    public String getEditPaymentDescriptionValue() {
        return paymentEditLightboxPaymentDescription.getAttribute("value");
    }

    public List<WebElement> findSuccessImportMessages() {
        return driver.findElements(By.id("ImportMessage"));
    }

    public List<WebElement> findFailureMessages() {
        return driver.findElements(By.id("ImportFailuresMessage_Message"));
    }

    public List<WebElement> findFailureReasonsWithText(String aReason) {
        return driver.findElements(By.xpath("//span[contains(text(), '" + aReason + "')]"));
    }

    public List<WebElement> findContractorLinksWithText(String aContractor) {
        return driver.findElements(By.xpath("//a[contains(text(), '" + aContractor + "')]"));
    }

    public List<WebElement> findSumsWithText(String aSum) {
        return driver.findElements(By.xpath("//div[@id='ItemsList']//span[contains(text(), '" + aSum + "')]"));
    }

    public List<WebElement> findDescriptionsWithText(String aDescription) {
        return driver.findElements(By.xpath("//span[contains(text(), '" + aDescription + "')]"));
    }

    public List<WebElement> findDatesWithText(String aDate) {
        return driver.findElements(By.xpath("//div[contains(text(), '" + aDate + "')]"));
    }

    public List<WebElement> findDocumentNumbersWithText(String aDocumentNumber) {
        return driver.findElements(By.xpath("//span[contains(text(), '" + aDocumentNumber + "')]"));
    }
}
