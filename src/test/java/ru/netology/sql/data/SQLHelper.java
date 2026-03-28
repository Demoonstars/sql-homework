package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        for (int i = 0; i < 15; i++) {
            try (var conn = getConn()) {
                String code = runner.query(conn, codeSQL, new ScalarHandler<String>());
                if (code != null) {
                    return code;
                }
            }
            Thread.sleep(1000);
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        try (var conn = getConn()) {
            runner.execute(conn, "DELETE FROM auth_codes");
            runner.execute(conn, "DELETE FROM card_transactions");
        }
    }
}