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

public class TestCardNumber {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openWebService() {
        open("http://localhost:8080/");
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }

    @AfterAll
    static void setUP() {
        SQLHelper.cleanDB();
    }

    @Test
    @DisplayName("Debit page: Error when entering short card number")
    void Test1() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val card = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithShortCardNumber();
        card.fillForm(authInfo);
        debitPage.errorMessageCardNumber();
    }

    @Test
    @DisplayName("Debit page: The field must not accept words")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getWordsInsteadOfCardNumberField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardNumber();
    }

    @Test
    @DisplayName("Debit page: The field must not accept symbols")
    void Test3() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSymbolInsteadOfCardNumberField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardNumber();
    }

    @Test
    @DisplayName("Credit page: Error when entering short card number")
    void Test4() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getPayWithShortCardNumber();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardNumber();
    }

    @Test
    @DisplayName("Credit page: The field must not accept words")
    void Test5() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getWordsInsteadOfCardNumberField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardNumber();
    }

    @Test
    @DisplayName("Credit page: The field must not accept symbols")
    void Test6() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSymbolInsteadOfCardNumberField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardNumber();
    }

}
