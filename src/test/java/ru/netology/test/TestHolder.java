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

public class TestHolder {
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
    @DisplayName("Debit page: The field must not accept rus words")
    void Test1() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithRusName();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Debit page: The field must not accept symbols")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardHolderField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Debit page: The field must not accept numbers")
    void Test3() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getNumberInsteadOfCardHolderField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Debit page: The field must accept only the first and last name of the holder")
    void Test4() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithOnlyName();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Credit page: The field must not accept rus words")
    void Test5() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getPayWithRusName();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Credit page: The field must not accept symbols")
    void Test6() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardHolderField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Credit page: The field must not accept numbers")
    void Test7() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getNumberInsteadOfCardHolderField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardHolder();
    }

    @Test
    @DisplayName("Credit page: The field must accept only the first and last name of the holder")
    void Test8() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getPayWithOnlyName();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardHolder();
    }

}
