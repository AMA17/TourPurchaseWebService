package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentGate;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PaymentGateTest {

    PaymentGate paymentGate;



    @Test
    void validInputTest() {
        open("http://localhost:8080");
        $(byText("Купить")).click();
        var numberCard = DataHelper.generateRandomNumberCard();
        var month = DataHelper.generateRandomMonth();
        var year = DataHelper.generateRandomYear();
        var holderCard = DataHelper.generateRandomNameHolderCard();
        var code = DataHelper.generatorRandomCode();
        $(byText("Продолжить")).click();

    }
}