package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentGate;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;


public class PaymentGateTest {

    PaymentGate paymentGate = new PaymentGate(); //Инициализировал страницу PaymentGate

    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

    @Test
    void validDataTest() { // валидные значения
        paymentGate.fillFormWithValidData();   //прошу выполнить метод validCard, который на странице PaymentGate
        paymentGate.textSuccessfullySees();
    }

    @Test
    void dataTest() {
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "33",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void invalidDataTest() {  // невалидное значение
        paymentGate.fillFormWithInvalidData();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void emptyFieldsTest() {  // незаполненные поля
        paymentGate.fillBlankFields();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void numberCardLatTest() { // номер карты латиницей
        paymentGate.fillSubstitutingValuesIntoFields("number", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void monthCardLatTest() { // месяц карты латиницей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "number", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void yearCardLatTest() { // год карты латиницей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "number",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void holderCardLatTest() { // владелец карты латиницей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Petr Van", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textSuccessfullySees();
    }

    @Test
    void codeCardLatTest() { // код карты латиницей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "number");
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void numberCardKirTest() { // номер карты кириллицей
        paymentGate.fillSubstitutingValuesIntoFields("номер", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void monthCardKirTest() { // месяц карты кириллицей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "номер", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void yearCardKirTest() { // год карты кириллицей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "номер",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void holderCardKirTest() { // владелец карты кириллицей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textSuccessfullySees();
    }

    @Test
    void codeCardKirTest() { // код карты кириллицей
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "номер");
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void doubleSurnameHolderTest() { // двойная фамилия владельца карты
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров-Смирнов Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textSuccessfullySees();
    }

    @Test
    void registerHolderTest() { // регистр у владельца карты
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "петров Иван", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textSuccessfullySees();
    }

    @Test
    void apostropheHolderTest() { // апостроф у владельца карты
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "D'art An", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textSuccessfullySees();
    }

    @Test
    void numberHolderTest() { // цифры в поле владельца карты
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "123456789", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsNumberCardTest() { // символы в номере карты
        paymentGate.fillSubstitutingValuesIntoFields("№*?;:", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsMonthCardTest() { // месяц карты символами
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "&*^", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsYearCardTest() { // год карты символами
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "*&",
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsHolderCardTest() { // владелец карты символами
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "&#$^)*#  $&", generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsCodeCardTest() { // код карты символами
        paymentGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "&^%&()");
        paymentGate.loginButton.click();
        paymentGate.textIncorrectFormatSees();
    }


    @Test
    void checkingTheStatusApprovedPaymentGate() { // Проверка статуса "APPROVED" в PaymentGate
        SQLHelper.cleanDatabase();                                       // очистка БД
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4441", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());  // заполнение данных
        paymentGate.loginButton.click();                                 // нажимает кнопку "Продолжить"
        paymentGate.WaitingForRequest();                                 // ожидание выполнения действия
        var actualStatus = SQLHelper.getPaymentStatus();                 // получение статуса из базы
        assertEquals("APPROVED", actualStatus);                          // сравнение результата
    }

    @Test
    void checkingTheStatusDeclinedPaymentGate() { // Проверка статуса "DECLINED" в PaymentGate
        SQLHelper.cleanDatabase();
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4442", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        paymentGate.loginButton.click();
        paymentGate.WaitingForRequest();
        var actualStatus = SQLHelper.getPaymentStatus();
        assertEquals("DECLINED", actualStatus);
    }

}