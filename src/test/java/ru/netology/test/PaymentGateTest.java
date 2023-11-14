package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentGate;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PaymentGateTest {
    PaymentGate paymentGate; //инициализировал страницу

    @BeforeEach
    void setup() {
        open("http://localhost:8080"); // открыл порт
    }

   // @Test
    //void validInputTest() {  // 1 способ - падает на строке №23 (наверное неправильно привязал поля)
       // $(byText("Купить")).click();  // нажал купить
       // var authInfo = DataHelper.generateRandomCard(); // инициализировал authInfo и взял рандомные данные
       // paymentGate.validCard(authInfo);  // на стр paymentGate заполнил данные
       // $(byText("Продолжить")).click();
       // Duration.ofSeconds(15);// нажал продолжить
    //}

   // @Test
        // Этот тест проходит, но не видно чтобы он подставлял данные (наверное неправильно привязал поля)
   // void InputTest() {
      //  $(byText("Купить")).click();
     //   var numberCard = DataHelper.generateRandomNumberCard();
      //  var year = DataHelper.generateRandomYear();
      //  var holderCard = DataHelper.generateRandomNameHolderCard();
      //  var code = DataHelper.generatorRandomCode();
     //   Duration.ofSeconds(15);
     //   $(byText("Продолжить")).click();
    //}

    @Test
    void InTest() {
        $(byText("Купить")).click();
        $("input.input__control").setValue(String.valueOf(DataHelper.generateRandomNumberCard()));
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(7));
    }


        //@Test
        //void validLoginTest() {
        //   $(byText("Купить")).click();
        //   $("class='input__top'>Номер карты<").setValue(DataHelper.generateRandomNumberCard());
        //   $("class='input__top'>Месяц<").setValue(String.valueOf(DataHelper.generateRandomMonth()));
        //   $("class='input__top'>Год<").setValue(String.valueOf(DataHelper.generateRandomYear()));
        //   $("class='input__top'>Владелец<").setValue(DataHelper.generateRandomNameHolderCard());
        //   $("class='input__top'>CVC/CVV<").setValue(String.valueOf(DataHelper.generatorRandomCode()));
        //   $(byText("Продолжить")).click();
        //}


    }