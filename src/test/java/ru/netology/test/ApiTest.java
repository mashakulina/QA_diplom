package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    @DisplayName("Debit card: Should check status with valid debit card")
    public void Test1() {
        val debitCard = DataHelper.getAuthInfo();
        val status = ApiHelper.fillPaymentFormByDebitCard(debitCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    @DisplayName("Debit card: Should check status with invalid debit card")
    public void Test2() {
        val debitCard = DataHelper.getPayWithDeclinedCardNumber();
        final String response = ApiHelper.fillPaymentFormByDebitCard(debitCard);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    @DisplayName("Debit card: Should check status without card number")
    public void Test3() {
        val debitCard = DataHelper.getPayWithoutCardNumber();
        final String response = ApiHelper.fillPaymentDebitFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Debit card: Should check status without card month")
    public void Test4() {
        val debitCard = DataHelper.getPayWithoutCardMonth();
        final String response = ApiHelper.fillPaymentDebitFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Debit card: Should check status without card year")
    public void Test5() {
        val debitCard = DataHelper.getPayWithoutCardYear();
        final String response = ApiHelper.fillPaymentDebitFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Debit card: Should check status without card holder")
    public void Test6() {
        val debitCard = DataHelper.getPayWithoutCardHolder();
        final String response = ApiHelper.fillPaymentDebitFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Debit card: Should check status without cvc code")
    public void Test7() {
        val debitCard = DataHelper.getPayWithoutCodeCVС();
        final String response = ApiHelper.fillPaymentDebitFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Credit card: Should check status with valid credit card")
    public void Test8() {
        val creditCard = DataHelper.getAuthInfo();
        final String response = ApiHelper.fillPaymentFormByCreditCard(creditCard);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    @DisplayName("Credit card: Should check status with invalid credit card")
    public void Test9() {
        val creditCard = DataHelper.getPayWithDeclinedCardNumber();
        final String response = ApiHelper.fillPaymentFormByCreditCard(creditCard);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    @DisplayName("Credit card: Should check status without card number")
    public void Test10() {
        val debitCard = DataHelper.getPayWithoutCardNumber();
        final String response = ApiHelper.fillPaymentCreditFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Credit card: Should check status without card month")
    public void Test11() {
        val debitCard = DataHelper.getPayWithoutCardMonth();
        final String response = ApiHelper.fillPaymentCreditFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Credit card: Should check status without card year")
    public void Test12() {
        val debitCard = DataHelper.getPayWithoutCardYear();
        final String response = ApiHelper.fillPaymentCreditFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Credit card: Should check status without card holder")
    public void Test13() {
        val debitCard = DataHelper.getPayWithoutCardHolder();
        final String response = ApiHelper.fillPaymentCreditFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }

    @Test
    @DisplayName("Credit card: Should check status without cvc code")
    public void Test14() {
        val debitCard = DataHelper.getPayWithoutCodeCVС();
        final String response = ApiHelper.fillPaymentCreditFormWithOneEmptyField(debitCard);
        assertTrue(response.contains("500"));
    }
}
