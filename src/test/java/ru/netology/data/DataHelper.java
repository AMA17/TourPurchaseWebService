package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String generateValidRandomNumberCard() { // метод рандомного номера карты
        String generateValidRandomNumberCard = faker.numerify("#### #### #### ####");
        return generateValidRandomNumberCard;
    }

    public static String invalidNumberCard() {
        String invalidNumberCard = faker.numerify("#### ####");
        return invalidNumberCard;
    }

    public static String generateRandomMonth() {
        //int number = faker.number().numberBetween(1, 12);
        //String formattedNumber = String.format("%02d", number);
        //return formattedNumber;
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return futureDate.format(formatter);
    }

    //public static int generateRandomYear() {  // метод рандомного года
    //   return faker.random().nextInt(23, 99);
    //}
    public static String getCurrentDatePlusOneYear() {
        LocalDate currentDate = LocalDate.now(); // Получаем текущую дату + год
        LocalDate futureDate = currentDate.plusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        return futureDate.format(formatter);
    }

    public static String generateRandomNameHolderCard() {  // метод рандомного владельца карты
        return faker.name().fullName();
    }

    public static String generatorRandomCode() {  // метод рандомного кода CVC/CVV
        int number = faker.number().numberBetween(1, 999);
        String formatNumber = String.format("%03d", number);
        return formatNumber;
    }

    public static AuthInfo generateRandomCard() {  // метод который принимает рандомные данные для карты
        return new AuthInfo(generateValidRandomNumberCard(), generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());
    }

    @Value
    public static class AuthInfo {  // метод который возвращает рандомные данные
        String numberCard;
        String month;
        String year;
        String holderCard;
        String code;
    }
    @Value
    public static class TransactionId {
        String id;
    }
}