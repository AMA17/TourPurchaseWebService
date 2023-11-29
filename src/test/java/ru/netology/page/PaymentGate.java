package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import ru.netology.data.DataHelper;

import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class PaymentGate {

    // инициализировал элементы на странице
    private final SelenideElement numberCardField = $(byText("Номер карты")).parent().$("input");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private final SelenideElement yearField = $(byText("Год")).parent().$("input");
    private final SelenideElement holderCardField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement codeField = $(byText("CVC/CVV")).parent().$("input");
    public final SelenideElement loginButton = $(byText("Продолжить"));
    private final SelenideElement buy = $(byText("Купить"));


    public void fillFormWithValidData() { // метод заполнения валидных данных
        buy.click();          // инициализировал поля и заполнил данными
        numberCardField.setValue(DataHelper.generateValidRandomNumberCard());
        monthField.setValue(DataHelper.generateRandomMonth());
        yearField.setValue(DataHelper.getCurrentDatePlusOneYear());
        holderCardField.setValue(DataHelper.generateRandomNameHolderCard());
        codeField.setValue(DataHelper.generatorRandomCode());
        loginButton.click();
    }

    public void fillFormWithInvalidData() { // метод невалидных данных
        buy.click();
        numberCardField.setValue(DataHelper.invalidNumberCard());
        monthField.setValue(DataHelper.generateRandomMonth());
        yearField.setValue(DataHelper.getCurrentDatePlusOneYear());
        holderCardField.setValue(DataHelper.generateRandomNameHolderCard());
        codeField.setValue(DataHelper.generatorRandomCode());
        loginButton.click();
    }

    public void fillSubstitutingValuesIntoFields(String number, String month, String year, String holder, String code) { // Метод для подсановки значений в поля
        buy.click();
        numberCardField.sendKeys(number);
        monthField.setValue(month);
        yearField.setValue(String.valueOf(year));
        holderCardField.setValue(holder);
        codeField.setValue(code);
    }

    public void fillBlankFields() { // Метод для незаполненных полей
        buy.click();
        loginButton.click();
    }

    public void textSuccessfullySees() {  // метод видит текст "Успешно"
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }


    public void textIncorrectFormatSees() {
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
}
