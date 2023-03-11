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

public class TestCVC {
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

    @AfterAll
    static void setUP() {
        SQLHelper.cleanDB();
    }


    @BeforeEach
    void openWebService() {
        open("http://localhost:8080/");
    }

    @Test
    @DisplayName("Debit page: The field must not exceed less than three")
    void Test1() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getCVСShorterThanThree();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardCVC();
    }

    @Test
    @DisplayName("Debit page: The field must not exceed more than three")
    void Test2() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getCVСLongerThanThree();
        debitCard.fillForm(authInfo);
        debitPage.errorPayWithLongCVC();
    }

    @Test
    @DisplayName("Debit page: The field must not accept words")
    void Test3() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getWordsInsteadOfCardCVСField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardCVC();
    }

    @Test
    @DisplayName("Debit page: The field must not accept symbols")
    void Test4() {
        val mainPage = new MainPage();
        val debitPage = new DebitPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardCVСField();
        debitCard.fillForm(authInfo);
        debitPage.errorMessageCardCVC();
    }

    @Test
    @DisplayName("Credit page: The field must not exceed less than three")
    void Test5() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getCVСShorterThanThree();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardCVC();
    }

    @Test
    @DisplayName("Credit page: The field must not exceed more than three")
    void Test6() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getCVСLongerThanThree();
        creditCard.fillForm(authInfo);
        creditPage.errorPayWithLongCVC();
    }

    @Test
    @DisplayName("Credit page: The field must not accept words")
    void Test7() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getWordsInsteadOfCardCVСField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardCVC();
    }

    @Test
    @DisplayName("Credit page: The field must not accept symbols")
    void Test8() {
        val mainPage = new MainPage();
        val creditPage = new CreditPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getSymbolsInsteadOfCardCVСField();
        creditCard.fillForm(authInfo);
        creditPage.errorMessageCardCVC();
    }
}
