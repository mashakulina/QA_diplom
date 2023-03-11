package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.DebitPage;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestForm {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openWebService() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void setUP() {
        SQLHelper.cleanDB();
    }

    @Test
    @DisplayName("Debit page: Form submitting with approved card number")
    void Test1() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getAuthInfo();
        debitCard.fillForm(authInfo);
        mainPage.transferWasApprove();
    }

    @Test
    @DisplayName("Debit page: Empty Form")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.emptyForm();
        debitPage.errorEmptyForm();
    }

    @Test
    @DisplayName("Debit page: Form not submitting with declined card number")
    void Test3() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithDeclinedCardNumber();
        debitCard.fillForm(authInfo);
        mainPage.transferWasDecline();
    }

    @Test
    @DisplayName("Credit page: Form submitting with approved card number")
    void Test4() {
        val mainPage = new MainPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getAuthInfo();
        creditCard.fillForm(authInfo);
        mainPage.transferWasApprove();
    }

    @Test
    @DisplayName("Credit page: Empty Form")
    void Test5() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        creditCard.emptyForm();
        creditPage.errorEmptyForm();
    }

    @Test
    @DisplayName("Credit page: Form not submitting with declined card number")
    void Test6() {
        val mainPage = new MainPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getPayWithDeclinedCardNumber();
        creditCard.fillForm(authInfo);
        mainPage.transferWasDecline();
    }

}
