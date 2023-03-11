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

public class TestYear {
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
    @DisplayName("Debit page: Should throw an error if the card year has expired")
    void Test1() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPyaWithLastCardYear();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardExpired();
    }

    @Test
    @DisplayName("Debit page: Should give an error if the year of the card is more than 5 years")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSevenYearsFromCurrentYearInsteadOfCardYearField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Debit page: The field must not accept words")
    void Test3() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getWordsInsteadOfCardYearField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Debit page: The field must not accept symbols")
    void Test4() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardYearField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Debit page: Should only take a two-digit value")
    void Test5() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getOneNumberInsteadOfCardYearField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Credit page: Should give an error if the year of the card is more than 5 years")
    void Test6() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSevenYearsFromCurrentYearInsteadOfCardYearField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Credit page: The field must not accept words")
    void Test7() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getWordsInsteadOfCardYearField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Credit page: The field must not accept symbols")
    void Test8() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardYearField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardYear();
    }

    @Test
    @DisplayName("Credit page: Should only take a two-digit value")
    void Test9() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getOneNumberInsteadOfCardYearField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardYear();
    }
}
