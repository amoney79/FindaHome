package com.findahome.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles database connection and schema initialization.
 */
public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:findahome.db";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Connected to database.");
            } catch (SQLException e) {
                System.err.println("Could not connect to database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void initialize() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create Properties table
            stmt.execute("CREATE TABLE IF NOT EXISTS properties (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "location TEXT," +
                    "price TEXT," +
                    "price_value REAL," +
                    "image_url TEXT," +
                    "verified INTEGER," + // 0 or 1
                    "tag TEXT," +
                    "beds TEXT," +
                    "baths TEXT," +
                    "sqft TEXT," +
                    "type TEXT" +
                    ")");

            // Create Users table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE," +
                    "password TEXT," +
                    "full_name TEXT," +
                    "role TEXT," +
                    "avatar_url TEXT," +
                    "verified INTEGER," + // 0 or 1
                    "member_since TEXT," +
                    "profile_strength REAL" +
                    ")");

            // Create VerificationRequests table
            stmt.execute("CREATE TABLE IF NOT EXISTS verification_requests (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "requester_name TEXT," + // Denormalized for ease
                    "request_type TEXT," + // AGENT, LANDLORD, PROPERTY
                    "status TEXT," + // PENDING, APPROVED, REJECTED
                    "date_requested TEXT," +
                    "details TEXT," +
                    "FOREIGN KEY(user_id) REFERENCES users(id)" +
                    ")");

            System.out.println("Database tables initialized.");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
