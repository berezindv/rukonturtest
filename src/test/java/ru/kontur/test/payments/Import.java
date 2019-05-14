package ru.kontur.test.payments;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.kontur.test.BaseTest;
import ru.kontur.test.utils.PaymentReader;

import static ru.kontur.test.TestData.*;

public class Import extends BaseTest {

    private static final String DUPLICATE_PAYMENT_MESSAGE = "Такой платёж уже добавлен в Эльбу";
    private static final String NOT_EXISTING_ACCOUNT_MESSAGE = "Укажите в Реквизитах расчётный счёт и снова загрузите файл с выпиской";
    private static final String WRONG_DATE_FORMAT_MESSAGE = "Не определили дату платежа";
    private static final String EDIT_PAYMENT_OPERATION_TYPE_VALUE = "Возврат переплаты по налогам";

    private PaymentReader paymentCorrect = new PaymentReader(PAYMENT_FILE_CORRECT);


    @BeforeClass
    void openPaymentsPage() {
        driver.get(ORGANIZATIONS_URI);
        driver.manage().window().maximize();

        elba.loginPage().login(USER, PASSWORD);
        elba.manageOrganizationsPage().selectOrganization();
        elba.mainMenu().openPaymentsPage();
    }

    @Test(priority = 1)
    void uploadCorrectPayment() {
        elba.paymentsPage().uploadPaymentFile(PAYMENT_FILE_CORRECT);

        Assert.assertEquals(elba.paymentsPage().findSuccessImportMessages().size(),1);

        Assert.assertEquals(elba.paymentsPage().findContractorLinksWithText(paymentCorrect.getContractor()).size(), 1);
        Assert.assertEquals(elba.paymentsPage().findSumsWithText(paymentCorrect.getSum()).size(), 1);
        Assert.assertEquals(elba.paymentsPage().findDescriptionsWithText(paymentCorrect.getDescription()).size(), 1);
        Assert.assertEquals(elba.paymentsPage().findDatesWithText(paymentCorrect.getDate()).size(), 1);
        Assert.assertEquals(elba.paymentsPage().findDocumentNumbersWithText(paymentCorrect.getDocumentNumber()).size(), 1);

        elba.paymentsPage().openEditPaymentLightBox();

        Assert.assertEquals(elba.paymentsPage().getEditPaymentOperationTypeValue(), EDIT_PAYMENT_OPERATION_TYPE_VALUE);
        Assert.assertEquals(elba.paymentsPage().getEditPaymentIncomeSumValue(), paymentCorrect.getSum());
        Assert.assertEquals(elba.paymentsPage().getEditPaymentTaxSumValue(), paymentCorrect.getTax());
        Assert.assertEquals(elba.paymentsPage().getEditPaymentDateValue(), paymentCorrect.getDate());
        Assert.assertEquals(elba.paymentsPage().getEditPaymentContractorNameValue(), paymentCorrect.getContractor());
        Assert.assertEquals(elba.paymentsPage().getEditPaymentDocumentNumberValue(), paymentCorrect.getDocumentNumber());
        Assert.assertEquals(elba.paymentsPage().getEditPaymentDescriptionValue(), paymentCorrect.getDescription());
    }

    @Test(priority = 2)
    void uploadDuplicatePayment() {
        elba.paymentsPage().uploadPaymentFile(PAYMENT_FILE_CORRECT);

        Assert.assertEquals(elba.paymentsPage().findFailureMessages().size(),1);

        elba.paymentsPage().seeFailureReason();

        Assert.assertEquals(elba.paymentsPage().findFailureReasonsWithText(DUPLICATE_PAYMENT_MESSAGE).size(), 1);
    }

    @Test(priority = 3)
    void uploadPaymentWithNotExistingAccount() {
        elba.paymentsPage().uploadPaymentFile(PAYMENT_FILE_WITH_NOT_EXISTING_ACCOUNT);

        Assert.assertEquals(elba.paymentsPage().findFailureMessages().size(),1);

        elba.paymentsPage().seeFailureReason();

        Assert.assertEquals(elba.paymentsPage().findFailureReasonsWithText(NOT_EXISTING_ACCOUNT_MESSAGE).size(), 1);
    }

    @Test(priority = 4)
    void uploadPaymentWithWrongDateFormat() {
        elba.paymentsPage().uploadPaymentFile(PAYMENT_FILE_WITH_WRONG_DATE_FORMAT);

        Assert.assertEquals(elba.paymentsPage().findFailureMessages().size(),1);

        elba.paymentsPage().seeFailureReason();

        Assert.assertEquals(elba.paymentsPage().findFailureReasonsWithText(WRONG_DATE_FORMAT_MESSAGE).size(), 1);
    }

    @AfterMethod
    void closeMessages() {
        if (elba.paymentsPage().reasonIsPresent()) {
            elba.paymentsPage().closeFailureReason();
            elba.paymentsPage().closeFailureMessage();
        }
        if (elba.paymentsPage().editPaymentIsPresent()) {
            elba.paymentsPage().closeEditPaymentLightBox();
        }
    }

    @AfterMethod
    void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            screenShoter.makeScreenshot(result.getName());
        }
    }

    @AfterClass
    void cleanUp() {
        elba.mainMenu().openContractorsPage();
        elba.contractorsPage().removeContractor(paymentCorrect.getContractor());
    }
}
