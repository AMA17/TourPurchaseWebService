package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreditGate {
    private final SelenideElement numberCard = $("class='input__top'>Номер карты<");
    private final SelenideElement month = $("class='input__top'>Месяц<");
    private final SelenideElement year = $("class='input__top'>Год<");
    private final SelenideElement owner = $("class='input__top'>Владелец<");
    private final SelenideElement code = $("class='input__top'>CVC/CVV<");
}
