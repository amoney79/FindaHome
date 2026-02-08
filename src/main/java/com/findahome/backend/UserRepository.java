package com.findahome.backend;

import com.findahome.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    // Basic CRUD
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("full_name"),
                            rs.getString("role"),
                            rs.getString("avatar_url"),
                            rs.getInt("verified") == 1,
                            rs.getString("member_since"),
                            rs.getDouble("profile_strength"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (username, full_name, role, avatar_url, verified, member_since, profile_strength) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getAvatarUrl());
            pstmt.setInt(5, user.isVerified() ? 1 : 0);
            pstmt.setString(6, user.getMemberSince());
            pstmt.setDouble(7, user.getProfileStrength());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Seed default user if none exists
    public void seedDefaultUser() {
        if (getUserByUsername("jdoe") == null) {
            addUser(new User("jdoe", "John Doe", "TENANT", "https://i.pravatar.cc/150?img=3", true, "April 2023",
                    0.85));
        }
    }
}
