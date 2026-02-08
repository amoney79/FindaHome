package com.findahome.backend;

import com.findahome.VerificationRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerificationRequestRepository {

    public List<VerificationRequest> getRequestsByStatus(String status) {
        List<VerificationRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM verification_requests WHERE status = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new VerificationRequest(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("requester_name"),
                            rs.getString("request_type"),
                            rs.getString("status"),
                            rs.getString("date_requested"),
                            rs.getString("details")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addRequest(VerificationRequest req) {
        String sql = "INSERT INTO verification_requests (user_id, requester_name, request_type, status, date_requested, details) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, req.getUserId());
            pstmt.setString(2, req.getRequesterName());
            pstmt.setString(3, req.getRequestType());
            pstmt.setString(4, req.getStatus());
            pstmt.setString(5, req.getDateRequested());
            pstmt.setString(6, req.getDetails());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
