package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
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

    @Test
    void codeCardLatTest() { // код карты латиницей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "number");
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void numberCardKirTest() { // номер карты кириллицей
        paymentGate.vc("номер", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void monthCardKirTest() { // месяц карты кириллицей
        paymentGate.vc(generateValidRandomNumberCard(), "номер", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void yearCardKirTest() { // год карты кириллицей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), "номер",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void holderCardKirTest() { // владелец карты кириллицей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void codeCardKirTest() { // код карты кириллицей
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "номер");
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void doubleSurnameHolderTest() { // двойная фамилия владельца карты
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров-Смирнов Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void registerHolderTest() { // регистр у владельца карты
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "петров Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void apostropheHolderTest() { // апостроф у владельца карты
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "D'art An", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void numberHolderTest() { // цифры в поле владельца карты
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "123456789", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void symbolsNumberCardTest() { // символы в номере карты
        paymentGate.vc("№*?;:", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void symbolsMonthCardTest() { // месяц карты символами
        paymentGate.vc(generateValidRandomNumberCard(), "&*^", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void symbolsYearCardTest() { // год карты символами
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), "*&",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void symbolsHolderCardTest() { // владелец карты символами
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "&#$^)*#  $&", generatorRandomCode());
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    void symbolsCodeCardTest() { // код карты символами
        paymentGate.vc(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "&^%&()");
        paymentGate.loginButton.click();
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
    @Test
    void TransactionId() {
        SQLHelper.getTransactionId();
    }

}