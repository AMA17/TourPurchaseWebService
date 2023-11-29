package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.page.PaymentGate;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.generatorRandomCode;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }


    @SneakyThrows
    public static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");

    }


    @SneakyThrows
    public static String getPaymentStatus() {  // метод делает запрос в базу и возвращает результат
        var conn = getConn();                                                            // подключается
        var codeSQL = "SELECT status FROM payment_entity pe ORDER BY created DESC LIMIT 1";   // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());     // runner выполняет подключение, передает код, возвращает строчное значение
        return actualStatus;
    }

    @SneakyThrows
    public static String getCreditStatus() {  // метод делает запрос в базу и возвращает результат
        var conn = getConn();                                                            // подключается
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";   // передаем запрос в БД
        var actualStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());     // runner выполняет подключение, передает код, возвращает строчное значение
        return actualStatus;
    }

}
