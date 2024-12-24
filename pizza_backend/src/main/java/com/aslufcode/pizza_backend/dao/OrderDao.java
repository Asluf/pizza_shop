package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.Order;
import com.aslufcode.pizza_backend.utils.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    public boolean saveOrder(Order order) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Orders (userEmail, mobile, pizzaName, crust, sauce, cheese, toppings, price, quantity, orderStatus, paymentStatus) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, order.getUserEmail());
            stmt.setString(2, order.getMobile());
            stmt.setString(3, order.getPizzaName());
            stmt.setString(4, order.getCrust());
            stmt.setString(5, order.getSauce());
            stmt.setString(6, order.getCheese());
            stmt.setString(7, order.getToppings());
            stmt.setDouble(8, order.getPrice());
            stmt.setInt(9, order.getQuantity());
            stmt.setString(10, order.getStatus());
            stmt.setString(11, "Paid");
            boolean orderSaved = stmt.executeUpdate() > 0;

            if (orderSaved) {
                String updateLoyaltyPointsQuery = "UPDATE User SET loyaltyPoints = loyaltyPoints + 1 WHERE email = ?";
                PreparedStatement updateLoyaltyPointsStmt = conn.prepareStatement(updateLoyaltyPointsQuery);
                updateLoyaltyPointsStmt.setString(1, order.getUserEmail());
                updateLoyaltyPointsStmt.executeUpdate();
            }

            return orderSaved;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getOrdersByUser(String userEmail) {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Orders WHERE userEmail = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserEmail(rs.getString("userEmail"));
                order.setMobile(rs.getString("mobile"));
                order.setPizzaName(rs.getString("pizzaName"));
                order.setCrust(rs.getString("crust"));
                order.setSauce(rs.getString("sauce"));
                order.setCheese(rs.getString("cheese"));
                order.setToppings(rs.getString("toppings"));
                order.setPrice(rs.getDouble("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStateFromString(rs.getString("orderStatus"));
                orders.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
