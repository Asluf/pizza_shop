package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.decorator.BaseOrder;
import com.aslufcode.pizza_backend.decorator.OrderComponent;
import com.aslufcode.pizza_backend.decorator.SpecialPackagingDecorator;
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
            OrderComponent orderComponent = new BaseOrder(order);
            if (order.isSpecialPackaging()) {
                orderComponent = new SpecialPackagingDecorator(orderComponent);
            }

            String query = "INSERT INTO Orders (userEmail, mobile, pizzaId, pizzaName, crust, sauce, cheese, toppings, price, quantity, orderStatus, paymentStatus, specialPackaging) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, order.getUserEmail());
            stmt.setString(2, order.getMobile());
            stmt.setInt(3, order.getPizzaId());
            stmt.setString(4, order.getPizzaName());
            stmt.setString(5, order.getCrust());
            stmt.setString(6, order.getSauce());
            stmt.setString(7, order.getCheese());
            stmt.setString(8, order.getToppings());
            stmt.setDouble(9, orderComponent.calculatePrice());
            stmt.setInt(10, order.getQuantity());
            stmt.setString(11, order.getStatus());
            stmt.setString(12, "Paid");
            stmt.setBoolean(13, order.isSpecialPackaging());

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
                order.setPizzaId(rs.getInt("pizzaId"));
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
