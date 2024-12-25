package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.Feedback;
import com.aslufcode.pizza_backend.utils.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class FeedbackDao {
    public void saveFeedback(Feedback feedback) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Feedback (userEmail, pizzaId, pizzaName, orderId, message) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, feedback.getUserEmail());
            stmt.setInt(2, feedback.getPizzaId());
            stmt.setString(3, feedback.getPizzaName());
            stmt.setInt(4, feedback.getOrderId());
            stmt.setString(5, feedback.getMessage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
