package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.page.PaymentGate;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.*;


public class PaymentGateTest {

    PaymentGate paymentGate; //Инициализировал страницу PaymentGate

    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

    @Test
    void validDataTest() {
        //var authinfo = DataHelper.generateRandomCard();
        paymentGate.validCard();   //прошу выполнить метод validCard, который на странице PaymentGate
        //paymentGate.vc("4444 4444 4444 44444", "05", "25", "Petr Van", "654")
        //paymentGate.vc(generateValidRandomNumberCard(),generateRandomMonth(), generateRandomYear(),
                //generateRandomNameHolderCard(), generatorRandomCode());
        $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
    }
    //@Test
   // void invalidDataTest() {
       // $(byText("Купить")).click();
      //  $(byText("Номер карты")).parent().$("input").setValue(String.valueOf(DataHelper.invalidNumberCard()));
       // $(byText("Месяц")).parent().$("input").setValue(String.valueOf(DataHelper.generateRandomMonth()));
       // $(byText("Год")).parent().$("input").setValue(String.valueOf(DataHelper.generateRandomYear()));
      //  $(byText("Владелец")).parent().$("input").setValue(DataHelper.generateRandomNameHolderCard());
       // $(byText("CVC/CVV")).parent().$("input").setValue(String.valueOf(DataHelper.generatorRandomCode()));
       // $(byText("Продолжить")).click();
       // $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
   // }
    //@Test
    //void emptyFieldsTest() {
       // $(byText("Купить")).click();
       // $(byText("Продолжить")).click();
       // $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    //}


}