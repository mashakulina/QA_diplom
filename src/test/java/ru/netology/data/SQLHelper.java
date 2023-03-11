package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


public class SQLHelper {
    private SQLHelper() {

    }

    static String url = getUrl();

    public static String getUrl() {
        return System.getProperty("db.url");
    }


    static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    public static Connection connect() {
        return DriverManager.getConnection(url, "user", "pass");
    }

    @SneakyThrows
    public static void cleanDB() {
        try (
                Connection conn = connect();
        ) {
            runner.update(conn, "DELETE FROM payment_entity");
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM credit_request_entity");
        }
    }


    @SneakyThrows
    public static String getPaymentStatus() {
        String paymentStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return runner.query(conn, paymentStatus, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getCreditRequestStatus() {
        String creditRequestStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return runner.query(conn, creditRequestStatus, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getCreatedOrderStatus() {
        String createdOrderStatus = "SELECT created FROM order_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss").format(runner.query(conn, createdOrderStatus, new ScalarHandler<>()));
        }
    }

    @SneakyThrows
    public static String getCreatedPaymentStatus() {
        String createdPaymentStatus = "SELECT created FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss").format(runner.query(conn, createdPaymentStatus, new ScalarHandler<>()));
        }
    }

    @SneakyThrows
    public static String getCreatedCreditRequestStatus() {
        String createdCreditRequestStatus = "SELECT created FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss").format(runner.query(conn, createdCreditRequestStatus, new ScalarHandler<>()));
        }
    }

    @SneakyThrows
    public static String getTransactionId() {
        String transactionID = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return runner.query(conn, transactionID, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getPaymentId() {
        String paymentID = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return runner.query(conn, paymentID, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getBankId() {
        String bankID = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = connect();
        ) {
            return runner.query(conn, bankID, new ScalarHandler<>());
        }
    }

//    для Postgres

    @SneakyThrows
    public static String getCreatedOrderStatusPostgres() {
        String createdOrderStatus = "SELECT created FROM order_entity ORDER BY created DESC LIMIT 1;";
        Timestamp created;
        try (
                Connection conn = connect();
        ) {
            created = runner.query(conn, createdOrderStatus, new ScalarHandler<>());
            return new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(created);
        }
    }

    @SneakyThrows
    public static String getCreatedPaymentStatusPostgres() {
        String createdPaymentStatus = "SELECT created FROM payment_entity ORDER BY created DESC LIMIT 1;";
        Timestamp created;
        try (
                Connection conn = connect();
        ) {
            created = runner.query(conn, createdPaymentStatus, new ScalarHandler<>());
            return new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(created);
        }
    }

    @SneakyThrows
    public static String getCreatedCreditRequestStatusPostgres() {
        String createdCreditRequestStatus = "SELECT created FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        Timestamp created;
        try (
                Connection conn = connect();
        ) {
            created = runner.query(conn, createdCreditRequestStatus, new ScalarHandler<>());
            return new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(created);
        }
    }

}
