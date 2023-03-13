package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CreditPage {
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement cardMonth = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement cardYear = $(byText("Год")).parent().$(".input__control");
    private SelenideElement cardHolder = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cardCVС = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    //    Ошибки
    private SelenideElement errorMessageCardNumber = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement errorMessageCardMonth = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement errorMessageCardYear = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement errorMessageCardHolder = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement errorMessageCardCVC = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement errorMessageCardExpired = $(byText("Истёк срок действия карты"));
    private SelenideElement errorMessageCardExpirationDate = $(byText("Неверно указан срок действия карты"));


    public void fillForm(DataHelper.AuthInfo info) {
        cardNumber.setValue(info.getNumber());
        cardMonth.setValue(info.getMonth());
        cardYear.setValue(info.getYear());
        cardHolder.setValue(info.getHolder());
        cardCVС.setValue(info.getCvc());
        continueButton.click();
    }

    public void emptyForm() {
        continueButton.click();
    }

    public void errorEmptyForm() {
        errorMessageCardNumber.shouldBe(visible);
        errorMessageCardMonth.shouldBe(visible);
        errorMessageCardYear.shouldBe(visible);
        errorMessageCardHolder.shouldBe(visible);
        errorMessageCardCVC.shouldBe(visible);
    }

    public void errorMessageCardNumber() {
        errorMessageCardNumber.shouldBe(visible);
    }

    public void errorMessageCardMonth() {
        errorMessageCardMonth.shouldBe(visible);
    }

    public void errorMessageCardYear() {
        errorMessageCardYear.shouldBe(visible);
    }

    public void errorMessageCardHolder() {
        errorMessageCardHolder.shouldBe(visible);
    }

    public void errorMessageCardCVC() {
        errorMessageCardCVC.shouldBe(visible);
    }

    public void errorMessageCardExpired() {
        errorMessageCardExpired.shouldBe(visible);
    }

    public void errorMessageCardMonthExpirationDate() {
        errorMessageCardExpirationDate.shouldBe(visible);
    }

    public void errorMessageCardYearExpirationDate() {
        errorMessageCardExpirationDate.shouldBe(visible);
    }

    public void errorPayWithLongCVC() {
        assertNotEquals(DataHelper.getCVCLongerThanThree(), cardCVС.getValue());
    }
}
