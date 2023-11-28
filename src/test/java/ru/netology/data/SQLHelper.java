package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.page.PaymentGate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.generatorRandomCode;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }


    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.Status getStatusApprovedPaymentGate() {
        var conn = getConn();                                                          // подключается
        runner.execute(conn, "DELETE FROM payment_entity");                            // чистит базу
        PaymentGate paymentGate = new PaymentGate();                                   // инициализация страницы
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4441", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());                // заполняет данные
        paymentGate.loginButton.click();                                               // нажимает "Продолжить"
        var codeSQL = "SELECT status FROM payment_entity pe LIMIT 1";                  // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());   // runner выполняет подключение, передает код, возвращает строчное значение
        var expectedStatus = "APPROVED";                                               // ожидаемый результат
        if (actualStatus == expectedStatus) {
            $(":contains('Успешно')").parent().$("div").shouldBe(visible, Duration.ofSeconds(10));
        } else {
            $(":contains('Ошибка')").parent().$("div").shouldBe(visible, Duration.ofSeconds(10));
        }
        return getStatusApprovedPaymentGate();

    }

    @SneakyThrows
    public static DataHelper.Status getStatusDeclinedPaymentGate() {
        var conn = getConn();                                                          // подключается
        runner.execute(conn, "DELETE FROM payment_entity");                            // чистит базу
        PaymentGate paymentGate = new PaymentGate();                                   // инициализация страницы
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4442", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());                // заполняет данные
        paymentGate.loginButton.click();                                               // нажимает "Продолжить"
        var codeSQL = "SELECT status FROM payment_entity pe LIMIT 1";                  // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());   // runner выполняет подключение, передает код, возвращает строчное значение
        var expectedStatus = "DECLINED";                                               // ожидаемый результат
        if (actualStatus == expectedStatus) {
            $(byText("Успешно")).parent().$(".notification__icon").shouldBe(visible, Duration.ofSeconds(10));
        } else {
            $(byText("Ошибка")).parent().$(".notification__icon").shouldBe(visible, Duration.ofSeconds(10));
        }
        return getStatusDeclinedPaymentGate();

    }
    @SneakyThrows
    public static DataHelper.Status getStatusApprovedCreditGate() {
        var conn = getConn();                                                          // подключается
        runner.execute(conn, "DELETE FROM credit_request_entity cre");                 // чистит базу
        PaymentGate paymentGate = new PaymentGate();                                   // инициализация страницы
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4441", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());                // заполняет данные
        paymentGate.loginButton.click();                                               // нажимает "Продолжить"
        var codeSQL = "SELECT status FROM credit_request_entity cre  LIMIT 1";         // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());   // runner выполняет подключение, передает код, возвращает строчное значение
        var expectedStatus = "APPROVED";                                               // ожидаемый результат
        if (actualStatus == expectedStatus) {
            $(":contains('Успешно')").parent().$("div").shouldBe(visible, Duration.ofSeconds(10));
        } else {
            $(":contains('Ошибка')").parent().$("div").shouldBe(visible, Duration.ofSeconds(10));
        }
        return getStatusApprovedCreditGate();

    }
    @SneakyThrows
    public static DataHelper.Status getStatusDeclinedCreditGate() {
        var conn = getConn();                                                          // подключается
        runner.execute(conn, "DELETE FROM credit_request_entity cre");                 // чистит базу
        PaymentGate paymentGate = new PaymentGate();                                   // инициализация страницы
        paymentGate.fillSubstitutingValuesIntoFields("4444 4444 4444 4442", generateRandomMonth(), getCurrentDatePlusOneYear(),
                generateRandomNameHolderCard(), generatorRandomCode());                // заполняет данные
        paymentGate.loginButton.click();                                               // нажимает "Продолжить"
        var codeSQL = "SELECT status FROM credit_request_entity cre  LIMIT 1";         // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());   // runner выполняет подключение, передает код, возвращает строчное значение
        var expectedStatus = "DECLINED";                                               // ожидаемый результат
        if (actualStatus == expectedStatus) {
            $(byText("Успешно")).parent().$(".notification__icon").shouldBe(visible, Duration.ofSeconds(10));
        } else {
            $(byText("Ошибка")).parent().$(".notification__icon").shouldBe(visible, Duration.ofSeconds(10));
        }
        return getStatusDeclinedCreditGate();

    }
}
