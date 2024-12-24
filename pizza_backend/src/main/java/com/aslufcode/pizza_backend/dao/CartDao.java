package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.Cart;
import com.aslufcode.pizza_backend.utils.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDao {
    public boolean addToCart(Cart cart) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Cart (userEmail, pizzaName, crust, sauce, cheese, toppings, price, totalPrice, quantity) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cart.getUserEmail());
            stmt.setString(2, cart.getPizzaName());
            stmt.setString(3, cart.getCrust());
            stmt.setString(4, cart.getSauce());
            stmt.setString(5, cart.getCheese());
            stmt.setString(6, cart.getToppings());
            stmt.setDouble(7, cart.getPrice());
            stmt.setDouble(8, cart.getTotalPrice());
            stmt.setInt(9, cart.getQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cart> getCartByUserEmail(String userEmail) {
        List<Cart> cartItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Cart WHERE userEmail = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cartItems.add(new Cart(
                        rs.getString("userEmail"),
                        rs.getString("pizzaName"),
                        rs.getString("crust"),
                        rs.getString("sauce"),
                        rs.getString("cheese"),
                        rs.getString("toppings"),
                        rs.getDouble("price"),
                        rs.getDouble("totalPrice"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public boolean updateCartQuantity(String userEmail, String pizzaName, int quantity) {
        try (Connection conn = DBConnection.getConnection()) {
            // Fetch the price of the pizza
            String fetchPriceQuery = "SELECT price FROM Cart WHERE userEmail = ? AND pizzaName = ?";
            PreparedStatement fetchPriceStmt = conn.prepareStatement(fetchPriceQuery);
            fetchPriceStmt.setString(1, userEmail);
            fetchPriceStmt.setString(2, pizzaName);
            ResultSet rs = fetchPriceStmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                double totalPrice = price * quantity;

                // Update the quantity and totalPrice
                String updateQuery = "UPDATE Cart SET quantity = ?, totalPrice = ? WHERE userEmail = ? AND pizzaName = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, quantity);
                updateStmt.setDouble(2, totalPrice);
                updateStmt.setString(3, userEmail);
                updateStmt.setString(4, pizzaName);
                return updateStmt.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeCartItem(String userEmail, String pizzaName) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM Cart WHERE userEmail = ? AND pizzaName = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userEmail);
            stmt.setString(2, pizzaName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
