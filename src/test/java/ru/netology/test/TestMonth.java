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

public class TestMonth {
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
    @DisplayName("Debit page: Should only take a two-digit value")
    void Test1() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getOneNumberInsteadOfCardMonthField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Debit page: Should give an error for the last month")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithLastCardMonth();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonthExpirationDate();
    }
    @Test
    @DisplayName("Debit page: The field must not accept words")
    void Test3() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getWordsInsteadOfCardMonthField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Debit page: Min boundary value")
    void Test4() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getMinBoundaryValueInsteadOfCardMonthField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Debit page: Max boundary value")
    void Test5() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getMaxBoundaryValueInsteadOfCardMonthField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Debit page: The field must not accept symbols")
    void Test6() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardMonthField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Credit page: Should only take a two-digit value")
    void Test7() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getOneNumberInsteadOfCardMonthField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Credit page: Should give an error for the last month")
    void Test8() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getPayWithLastCardMonth();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonthExpirationDate();
    }
    @Test
    @DisplayName("Credit page: The field must not accept words")
    void Test9() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getWordsInsteadOfCardMonthField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Credit page: Min boundary value")
    void Test10() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getMinBoundaryValueInsteadOfCardMonthField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Credit page: Max boundary value")
    void Test11() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getMaxBoundaryValueInsteadOfCardMonthField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonth();
    }
    @Test
    @DisplayName("Credit page: The field must not accept symbols")
    void Test12() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardMonthField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardMonth();
    }
}
