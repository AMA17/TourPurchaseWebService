package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }
    @SneakyThrows
    public static DataHelper.TransactionId getTransactionId() {
        var codeSQL = "SELECT transaction_id  FROM payment_entity pe LIMIT 1"; // передаем запрос в БД
        var conn = getConn();                                                  // в переменную помещаем подключение (метод getConn)
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());   // runner выполняет подключение, передает код, возвращает строчное значение
        return new DataHelper.TransactionId(code);                             // возвращает строку
    }

}
