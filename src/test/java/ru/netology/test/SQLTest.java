package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLTest {
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
    @SneakyThrows
    @DisplayName("Payment with first given card should be approved and saved by database")
    void Test1() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getAuthInfo();
        debitCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @SneakyThrows
    @DisplayName("Payment with second given card should be declined and saved by database")
    void Test2() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getPayWithDeclinedCardNumber();
        debitCard.fillForm(authInfo);
        mainPage.transferWasDecline();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getCreditRequestStatus());
    }

    @Test
    @SneakyThrows
    @DisplayName("Only for MySQL: Payment card info can be sent and saved by database (Date)")
    void Test3() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getAuthInfo();
        debitCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedPaymentStatus());
    }

    @Test
    @SneakyThrows
    @DisplayName("Payment card info can be sent and saved by database (ID)")
    void Test4() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getAuthInfo();
        debitCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getTransactionId(), SQLHelper.getPaymentId());
    }

    @Test
    @SneakyThrows
    @DisplayName("Only for MySQL: Credit request card info can be sent and saved by database (Date)")
    void Test5() {
        val mainPage = new MainPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getAuthInfo();
        creditCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedCreditRequestStatus());
    }

    @Test
    @SneakyThrows
    @DisplayName("Credit request card info can be sent and saved by database (ID)")
    void Test6() {
        val mainPage = new MainPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getAuthInfo();
        creditCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getBankId(), SQLHelper.getPaymentId());
    }

    // для Postgres
    @Test
    @SneakyThrows
    @DisplayName("Only for Postgres: Payment card info can be sent and saved by database (Date)")
    void Test7() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val authInfo = DataHelper.getAuthInfo();
        debitCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getCreatedOrderStatusPostgres(), SQLHelper.getCreatedPaymentStatusPostgres());
    }

    @Test
    @SneakyThrows
    @DisplayName("Only for Postgres: Credit request card info can be sent and saved by database (Date)")
    void Test8() {
        val mainPage = new MainPage();
        val creditCard = mainPage.payWithCreditCard();
        val authInfo = DataHelper.getAuthInfo();
        creditCard.fillForm(authInfo);
        mainPage.transferWasApprove();
        assertEquals(SQLHelper.getCreatedOrderStatusPostgres(), SQLHelper.getCreatedCreditRequestStatusPostgres());
    }


}
