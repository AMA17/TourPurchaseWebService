package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGate {
    // инициализировал элементы на странице
    private final SelenideElement numberCard = $(byText("Номер карты"));
    private final SelenideElement month = $(byText("Месяц"));
    private final SelenideElement year = $(byText("Год"));
    private final SelenideElement holderCard = $(byText("Владелец"));
    private final SelenideElement code = $(byText("CVC/CVV"));
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
