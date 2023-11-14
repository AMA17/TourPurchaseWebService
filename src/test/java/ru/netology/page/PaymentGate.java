package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGate {
    // инициализировал элементы на странице
    private final SelenideElement numberCard = $("class='input__top'>Номер карты<");
    private final SelenideElement month = $("class='input__top'>Месяц<");
    private final SelenideElement year = $("class='input__top'>Год<");
    private final SelenideElement holderCard = $("class='input__top'>Владелец<");
    private final SelenideElement code = $("div.form-field form-field_size_m form-field_theme_alfa-on-white").find("input[class='input__control']");
    private final SelenideElement loginButton = $(byText("Продолжить"));
    private final SelenideElement buy = $(byText("Купить"));

    public PaymentGate validCard(DataHelper.AuthInfo info) { // метод заполнения валидных данных
        buy.click();                                         // не заполняет потому что неправильно привязал поля?
        numberCard.setValue(info.getNumberCard());
        month.setValue(String.valueOf(info.getMonth()));
        year.setValue(String.valueOf(info.getYear()));
        holderCard.setValue(info.getHolderCard());
        code.setValue(String.valueOf(info.getCode()));
        loginButton.click();
        return new PaymentGate();
    }
    public PaymentGate invalidCard(DataHelper.AuthInfo info) { // тут будет метод невалидных данных
        buy.click();                                           // как создать невалидные данные?
        numberCard.setValue(info.getNumberCard());             // захардкодить валидные, а невал сгенирировать?
        month.setValue(String.valueOf(info.getMonth()));
        year.setValue(String.valueOf(info.getYear()));
        holderCard.setValue(info.getHolderCard());
        code.setValue(String.valueOf(info.getCode()));
        loginButton.click();
        return new PaymentGate();
    }
}
