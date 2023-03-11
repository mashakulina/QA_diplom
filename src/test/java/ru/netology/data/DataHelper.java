package ru.netology.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {

    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String approvedCardNumber = "4444 4444 4444 4441";
    public static String declinedCardNumber = "4444 4444 4444 4442";
    public static String cardHolder = "PETR PETROV";
    public static String cardHolderOnlyName = "PETR";
    public static String cardHolderRus = "ПЕТР ПЕТРОВ";
    public static String randomSymbol = "!\"№;%:?";
    public static String randomWords = "qwertyйцукен";
    public static String approvedStatus = "APPROVED";
    public static String declinedStatus = "DECLINED";

    public static String getAnotherCardNumber() {
        return faker.finance().creditCard(CreditCardType.MASTERCARD);
    }

    public static String getShortCardNumber() {
        return faker.finance().creditCard(CreditCardType.AMERICAN_EXPRESS);
    }

    public static String getCardMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastCardMonth() {
        return LocalDate.now().minusMonths(2).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCardYear() {
        return String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")) + 2);
    }

    public static String getLastCardYear() {
        return LocalDate.now().minusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getSevenYearsFromCurrentYear() {
        return LocalDate.now().plusYears(7).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCodeCVС() {
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    public static String getCVCShorterThanThree() {
        return String.valueOf(faker.number().numberBetween(1, 90));
    }

    public static String getCVCLongerThanThree() {
        return String.valueOf(faker.number().numberBetween(1000, 9999));
    }

    public static String getOneNumber() {
        return String.valueOf(faker.number().randomDigit());
    }

    public static String getRandomNumber() {
        return String.valueOf(faker.number().randomNumber());
    }

    @Value
    public static class AuthInfo {
        private String cardNumber;
        private String cardMonth;
        private String cardYear;
        private String cardHolder;
        private String cardCVС;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    //    Поле Номер карты
    public static AuthInfo getPayWithDeclinedCardNumber() {
        return new AuthInfo(declinedCardNumber, getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getPayWithAnotherCardNumber() {
        return new AuthInfo(getAnotherCardNumber(), getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getPayWithShortCardNumber() {
        return new AuthInfo(getShortCardNumber(), getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getSymbolInsteadOfCardNumberField() {
        return new AuthInfo(randomSymbol, getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getWordsInsteadOfCardNumberField() {
        return new AuthInfo(randomWords, getCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    //    Поле Месяц
    public static AuthInfo getPayWithLastCardMonth() {
        return new AuthInfo(approvedCardNumber, getLastCardMonth(), getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getSymbolsInsteadOfCardMonthField() {
        return new AuthInfo(approvedCardNumber, randomSymbol, getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getWordsInsteadOfCardMonthField() {
        return new AuthInfo(approvedCardNumber, randomWords, getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getMinBoundaryValueInsteadOfCardMonthField() {
        return new AuthInfo(approvedCardNumber, "00", getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getMaxBoundaryValueInsteadOfCardMonthField() {
        return new AuthInfo(approvedCardNumber, "13", getCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getOneNumberInsteadOfCardMonthField() {
        return new AuthInfo(approvedCardNumber, getOneNumber(), getCardYear(), cardHolder, getCodeCVС());
    }

    //    Поле Год
    public static AuthInfo getPyaWithLastCardYear() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getLastCardYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getSevenYearsFromCurrentYearInsteadOfCardYearField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getSevenYearsFromCurrentYear(), cardHolder, getCodeCVС());
    }

    public static AuthInfo getSymbolsInsteadOfCardYearField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), randomSymbol, cardHolder, getCodeCVС());
    }

    public static AuthInfo getWordsInsteadOfCardYearField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), randomWords, cardHolder, getCodeCVС());
    }

    public static AuthInfo getOneNumberInsteadOfCardYearField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getOneNumber(), cardHolder, getCodeCVС());
    }

    //    Поле Владелец
    public static AuthInfo getPayWithRusName() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolderRus, getCodeCVС());
    }

    public static AuthInfo getPayWithOnlyName() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolderOnlyName, getCodeCVС());
    }

    public static AuthInfo getSymbolsInsteadOfCardHolderField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), randomSymbol, getCodeCVС());
    }

    public static AuthInfo getNumberInsteadOfCardHolderField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), getRandomNumber(), getCodeCVС());
    }

    //    Поле CVС
    public static AuthInfo getCVСLongerThanThree() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolder, getCVCLongerThanThree());
    }

    public static AuthInfo getCVСShorterThanThree() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolder, getCVCShorterThanThree());
    }

    public static AuthInfo getSymbolsInsteadOfCardCVСField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolder, randomSymbol);
    }

    public static AuthInfo getWordsInsteadOfCardCVСField() {
        return new AuthInfo(approvedCardNumber, getCardMonth(), getCardYear(), cardHolder, randomWords);
    }

}
