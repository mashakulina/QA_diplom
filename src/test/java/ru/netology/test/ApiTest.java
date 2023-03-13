package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    @DisplayName("Should check status with valid debit card")
    public void Test1() {
        val debitCard = DataHelper.getAuthInfo();
        val status = ApiHelper.fillPaymentFormByDebitCard(debitCard);
        assertTrue(status.contains("APPROVED"));
    }
    @Test
    @DisplayName("Should check status with invalid debit card")
    public void Test2() {
        val debitCard = DataHelper.getPayWithDeclinedCardNumber();
        final String response = ApiHelper.fillPaymentFormByDebitCard(debitCard);
        assertTrue(response.contains("DECLINED"), "Is true when status is declined");
    }
    @Test
    @DisplayName("Should check status with valid credit card")
    public void Test3() {
        val creditCard = DataHelper.getAuthInfo();
        final String response = ApiHelper.fillPaymentFormByCreditCard(creditCard);
        assertTrue(response.contains("APPROVED"), "Is true when status is approved");
    }
    @Test
    @DisplayName("Should check status with invalid credit card")
    public void Test4() {
        val creditCard = DataHelper.getPayWithDeclinedCardNumber();
        final String response = ApiHelper.fillPaymentFormByCreditCard(creditCard);
        assertTrue(response.contains("DECLINED"), "Is true when status is declined");
    }
}
