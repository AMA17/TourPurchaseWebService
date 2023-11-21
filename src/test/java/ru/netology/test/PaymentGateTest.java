package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.page.PaymentGate;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.*;


public class PaymentGateTest {

    PaymentGate paymentGate = new PaymentGate(); //Инициализировал страницу PaymentGate

    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

    @Test
    void validDataTest() { // валидные значения
        paymentGate.validCard();   //прошу выполнить метод validCard, который на странице PaymentGate
        //paymentGate.vc("4444 4444 4444 44444", "05", "24", "Petr Van", "654");
        //paymentGate.vc(generateValidRandomNumberCard(),generateRandomMonth(), generateRandomYear(),
        //generateRandomNameHolderCard(), generatorRandomCode());
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void dataTest() {
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), "33",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void invalidDataTest() {  // невалидное значение
        paymentGate.invalidCard();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void emptyFieldsTest() {  // незаполненные поля
    paymentGate.vc();
    $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void numberCardLatTest() { // номер карты латиницей
        paymentGate.vc("number", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void monthCardLatTest() { // месяц карты латиницей
        paymentGate.vc(generateValidRandomNumberCard(), "number", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void yearCardLatTest() { // год карты латиницей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), "number",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void holderCardLatTest() { // владелец карты латиницей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Petr Van", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }


}