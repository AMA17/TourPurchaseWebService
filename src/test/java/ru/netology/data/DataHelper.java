package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String generateRandomNumberCard() { // метод рандомного номера карты
        return faker.finance().creditCard();
    }

    public static int generateRandomMonth() {  // метод рандомного месяца
        return faker.number().numberBetween(01, 13);
    }

    public static int generateRandomYear() {  // метод рандомного года
        return faker.random().nextInt(3000 - 1900 + 1) + 1900;
    }

    public static String generateRandomNameHolderCard() {  // метод рандомного владельца карты
        return faker.name().fullName();
    }

    public static int generatorRandomCode() {  // метод рандомного кода CVC/CVV
        return faker.number().numberBetween(100, 999);
    }

    public static AuthInfo generateRandomCard() {  // метод который принимает рандомные данные для карты
        return new AuthInfo(generateRandomNumberCard(), generateRandomMonth(), generateRandomYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
    }

    @Value
    public static class AuthInfo {  // метод который возвращает рандомные данные
        String numberCard;
        int month;
        int year;
        String holderCard;
        int code;
    }
}