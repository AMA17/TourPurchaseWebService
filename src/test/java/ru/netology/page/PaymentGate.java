package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class PaymentGate {

    private final SelenideElement numberCard = $("class='input__top'>Номер карты<");
    private final SelenideElement month = $("class='input__top'>Месяц<");
    private final SelenideElement year = $("class='input__top'>Год<");
    private final SelenideElement holderCard = $("class='input__top'>Владелец<");
    private final SelenideElement code = $("class='input__top'>CVC/CVV<");
    private final SelenideElement loginButton = $("class='button__text'");
    private final SelenideElement buy = $("class='button__content'");

    public PaymentGate validCard(DataHelper.AuthInfo info) {
        buy.click();
        numberCard.setValue(info.getNumberCard());
        month.setValue(String.valueOf(info.getMonth()));
        year.setValue(String.valueOf(info.getYear()));
        holderCard.setValue(info.getHolderCard());
        code.setValue(String.valueOf(info.getCode()));
        loginButton.click();
        return new PaymentGate();
    }
}
