package com.findahome.backend;

import com.findahome.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {

    public void addProperty(Property property) {
        String sql = "INSERT INTO properties (name, location, price, price_value, image_url, verified, tag, beds, baths, sqft, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, property.getName());
            pstmt.setString(2, property.getLocation());
            pstmt.setString(3, property.getPrice());
            pstmt.setDouble(4, property.getPriceValue());
            pstmt.setString(5, property.getImageUrl());
            pstmt.setInt(6, property.isVerified() ? 1 : 0);
            pstmt.setString(7, property.getTag());
            pstmt.setString(8, property.getBeds());
            pstmt.setString(9, property.getBaths());
            pstmt.setString(10, property.getSqft());
            pstmt.setString(11, property.getType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                properties.add(new Property(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("price"),
                        rs.getDouble("price_value"),
                        rs.getString("image_url"),
                        rs.getInt("verified") == 1,
                        rs.getString("tag"),
                        rs.getString("beds"),
                        rs.getString("baths"),
                        rs.getString("sqft"),
                        rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public List<Property> filterProperties(String type, String location, double minPrice, double maxPrice) {
        List<Property> properties = new ArrayList<>();
        // Basic SQL filtering (can be optimized)
        StringBuilder sql = new StringBuilder("SELECT * FROM properties WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (type != null && !type.equals("All Types") && !type.isEmpty()) {
            sql.append(" AND type LIKE ?");
            params.add("%" + type + "%");
        }
        if (location != null && !location.isEmpty()) {
            sql.append(" AND location LIKE ?");
            params.add("%" + location + "%");
        }

        sql.append(" AND price_value >= ? AND price_value <= ?");
        params.add(minPrice);
        params.add(maxPrice);

        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    properties.add(new Property(
                            rs.getString("name"),
                            rs.getString("location"),
                            rs.getString("price"),
                            rs.getDouble("price_value"),
                            rs.getString("image_url"),
                            rs.getInt("verified") == 1,
                            rs.getString("tag"),
                            rs.getString("beds"),
                            rs.getString("baths"),
                            rs.getString("sqft"),
                            rs.getString("type")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Check if empty (to seed data)
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM properties";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
