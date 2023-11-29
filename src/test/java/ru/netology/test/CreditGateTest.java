package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.SQLHelper;
import ru.netology.page.CreditGate;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;


public class CreditGateTest {

    CreditGate creditGate = new CreditGate(); //Инициализировал страницу PaymentGate

    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

    @Test
    void validDataTest() { // валидные значения
        creditGate.fillFormWithValidData();   //прошу выполнить метод validCard, который на странице PaymentGate
        creditGate.textSuccessfullySees();
    }

    @Test
    void dataTest() {
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "33",
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void invalidDataTest() {  // невалидное значение
        creditGate.fillFormWithInvalidData();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void emptyFieldsTest() {  // незаполненные поля
        creditGate.fillBlankFields();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void numberCardLatTest() { // номер карты латиницей
        creditGate.fillSubstitutingValuesIntoFields("number", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void monthCardLatTest() { // месяц карты латиницей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "number", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void yearCardLatTest() { // год карты латиницей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "number",
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void holderCardLatTest() { // владелец карты латиницей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Petr Van", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textSuccessfullySees();
    }

    @Test
    void codeCardLatTest() { // код карты латиницей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "number");
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void numberCardKirTest() { // номер карты кириллицей
        creditGate.fillSubstitutingValuesIntoFields("номер", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void monthCardKirTest() { // месяц карты кириллицей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "номер", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void yearCardKirTest() { // год карты кириллицей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "номер",
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void holderCardKirTest() { // владелец карты кириллицей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров Иван", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textSuccessfullySees();
    }

    @Test
    void codeCardKirTest() { // код карты кириллицей
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "номер");
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void doubleSurnameHolderTest() { // двойная фамилия владельца карты
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "Петров-Смирнов Иван", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textSuccessfullySees();
    }

    @Test
    void registerHolderTest() { // регистр у владельца карты
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "петров Иван", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textSuccessfullySees();
    }

    @Test
    void apostropheHolderTest() { // апостроф у владельца карты
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "D'art An", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textSuccessfullySees();
    }

    @Test
    void numberHolderTest() { // цифры в поле владельца карты
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "123456789", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsNumberCardTest() { // символы в номере карты
        creditGate.fillSubstitutingValuesIntoFields("№*?;:", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsMonthCardTest() { // месяц карты символами
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), "&*^", getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsYearCardTest() { // год карты символами
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), "*&",
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsHolderCardTest() { // владелец карты символами
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                "&#$^)*#  $&", generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void symbolsCodeCardTest() { // код карты символами
        creditGate.fillSubstitutingValuesIntoFields(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), "&^%&()");
        creditGate.loginButton.click();
        creditGate.textIncorrectFormatSees();
    }

    @Test
    void checkingTheStatusApprovedCreditGate() { // Проверка статуса "APPROVED" в CreditGate
        SQLHelper.cleanDatabase();
        creditGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4441", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.WaitingForRequest();
        var actualStatus = SQLHelper.getCreditStatus();
        assertEquals("APPROVED", actualStatus);
    }


    @Test
    void checkingTheStatusDeclinedCreditGate() { // Проверка статуса "DECLINED" в CreditGate
        SQLHelper.cleanDatabase();

        creditGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4442", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
        creditGate.loginButton.click();
        creditGate.WaitingForRequest();
        var actualStatus = SQLHelper.getCreditStatus();
        assertEquals("DECLINED", actualStatus);
    }
}