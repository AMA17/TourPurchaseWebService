package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreditGateTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

    @Test
    void validDataTest() {
        $(byText("Купить в кредит")).click();
        $(byText("Номер карты")).parent().$("input").setValue(String.valueOf(DataHelper.generateValidRandomNumberCard()));
        $(byText("Месяц")).parent().$("input").setValue(String.valueOf(DataHelper.generateRandomMonth()));
        $(byText("Год")).parent().$("input").setValue(String.valueOf(DataHelper.getCurrentDatePlusOneYear()));
        $(byText("Владелец")).parent().$("input").setValue(DataHelper.generateRandomNameHolderCard());
        $(byText("CVC/CVV")).parent().$("input").setValue(String.valueOf(DataHelper.generatorRandomCode()));
        $(byText("Продолжить")).click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void invalidDataTest() {
        $(byText("Купить в кредит")).click();
        $(byText("Номер карты")).parent().$("input").setValue(String.valueOf(DataHelper.invalidNumberCard()));
        $(byText("Месяц")).parent().$("input").setValue(String.valueOf(DataHelper.generateRandomMonth()));
        $(byText("Год")).parent().$("input").setValue(String.valueOf(DataHelper.getCurrentDatePlusOneYear()));
        $(byText("Владелец")).parent().$("input").setValue(DataHelper.generateRandomNameHolderCard());
        $(byText("CVC/CVV")).parent().$("input").setValue(String.valueOf(DataHelper.generatorRandomCode()));
        $(byText("Продолжить")).click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void emptyFieldsTest() {
        $(byText("Купить в кредит")).click();
        $(byText("Продолжить")).click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
}
