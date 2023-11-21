package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGate {

    // инициализировал элементы на странице
    private final SelenideElement numberCardField = $(byText("Номер карты")).parent().$("input");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private final SelenideElement yearField = $(byText("Год")).parent().$("input");
    private final SelenideElement holderCardField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement codeField = $(byText("CVC/CVV")).parent().$("input");
    private final SelenideElement loginButton = $(byText("Продолжить"));
    private final SelenideElement buy = $(byText("Купить"));

    public void validCard() { // метод заполнения валидных данных
        buy.click();          // инициализировал поля и заполнил данными
        numberCardField.setValue(DataHelper.generateValidRandomNumberCard());
        monthField.setValue(DataHelper.generateRandomMonth());
        yearField.setValue(String.valueOf(DataHelper.generateRandomYear()));
        holderCardField.setValue(DataHelper.generateRandomNameHolderCard());
        codeField.setValue(DataHelper.generatorRandomCode());
        loginButton.click();
    }
    public PaymentGate invalidCard(DataHelper.AuthInfo info) { // тут будет метод невалидных данных
        buy.click();                                           // Пробовал реализовать метод по-другому
        numberCardField.setValue(info.getNumberCard());        //
        monthField.setValue((info.getMonth()));
        yearField.setValue(String.valueOf(info.getYear()));
        holderCardField.setValue(info.getHolderCard());
        codeField.setValue(info.getCode());
        loginButton.click();
        return new PaymentGate();
    }

    public void vc(String number, String month, int year, String holder, String code) {
        buy.click();
        numberCardField.sendKeys(number);
        monthField.setValue(month);
        yearField.setValue(String.valueOf(year));
        holderCardField.setValue(holder);
        codeField.setValue(code);
    }
}
