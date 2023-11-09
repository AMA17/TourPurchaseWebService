package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String generateRandomNumberCard() {
        return faker.finance().creditCard();
    }

    public static int generateRandomMonth() {
        return faker.number().numberBetween(01, 13);
    }

    public static int generateRandomYear() {
        return faker.random().nextInt(3000 - 1900 + 1) + 1900;
    }

    public static String generateRandomNameHolderCard() {
        return faker.name().fullName();
    }

    public static int generatorRandomCode() {
        return faker.number().numberBetween(100, 999);
    }

    public static AuthInfo generateRandomCard() {
        return new AuthInfo(generateRandomNumberCard(), generateRandomMonth(), generateRandomYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
    }

    @Value
    public static class AuthInfo {
        String numberCard;
        int month;
        int year;
        String holderCard;
        int code;
    }
}