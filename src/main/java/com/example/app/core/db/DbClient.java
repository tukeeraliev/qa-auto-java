package com.example.app.core.db;

import java.sql.*;

public class DbClient {

    private static final String URL = "jdbc:postgresql://localhost:5433/qa";
    private static final String USER = "qa_user";
    private static final String PASSWORD = "qa_pass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // создаёт таблицу если её нет + добавляет api_id если таблица была старой
    public static void initSchema() throws SQLException {
        String createTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                    id SERIAL PRIMARY KEY,
                    firstname VARCHAR(50),
                    lastname VARCHAR(50),
                    totalprice INT,
                    api_id INT
                );
                """;

        String addColumnIfMissing = """
                ALTER TABLE bookings
                ADD COLUMN IF NOT EXISTS api_id INT;
                """;

        String createUniqueIndex = """
                CREATE UNIQUE INDEX IF NOT EXISTS ux_bookings_api_id
                ON bookings(api_id);
                """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
            stmt.execute(addColumnIfMissing);
            stmt.execute(createUniqueIndex);
        }
    }

    // очищает таблицу перед тестом
    public static void clearBookings() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE bookings RESTART IDENTITY;");
        }
    }

    // вставляет запись и возвращает id (ТВОЙ старый вариант — оставил)
    public static int insertBooking(String firstname, String lastname, int totalprice) throws SQLException {
        String sql = "INSERT INTO bookings(firstname, lastname, totalprice) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, totalprice);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    // НОВОЕ: вставка с api_id (для связки API -> DB)
    public static int insertBookingWithApiId(int apiId, String firstname, String lastname, int totalprice) throws SQLException {
        String sql = "INSERT INTO bookings(api_id, firstname, lastname, totalprice) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, apiId);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setInt(4, totalprice);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    // НОВОЕ: проверить, что запись с api_id есть
    public static boolean existsByApiId(int apiId) throws SQLException {
        String sql = "SELECT 1 FROM bookings WHERE api_id = ? LIMIT 1";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, apiId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // считает записи
    public static int countBookings() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT count(*) FROM bookings")) {

            rs.next();
            return rs.getInt(1);
        }
    }
}