package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement mainPage = $(byText("Путешествие дня"));
    private SelenideElement debitCardButton = $(byText("Купить"));
    private SelenideElement creditCardButton = $(byText("Купить в кредит"));
    private SelenideElement debitFormName = $(byText("Оплата по карте"));
    private SelenideElement creditFormName = $(byText("Кредит по данным карты"));
    private SelenideElement approveMessage = $(byText("Успешно"));
    private SelenideElement declineMessage = $(byText("Ошибка"));

    public MainPage() {
        mainPage.shouldBe(visible);
    }

    public DebitPage payWithDebitCard() {
        debitCardButton.click();
        debitFormName.shouldBe(visible);
        return new DebitPage();
    }

    public CreditPage payWithCreditCard() {
        creditCardButton.click();
        creditFormName.shouldBe(visible);
        return new CreditPage();
    }

    public void transferWasApprove() {
        approveMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    public void transferWasDecline() {
        declineMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

}
