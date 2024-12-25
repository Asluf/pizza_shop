package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.Favorite;
import com.aslufcode.pizza_backend.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class FavoriteDao {
    public boolean addFavorite(Favorite favorite) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Favorite (userEmail, pizzaId, pizzaName, crust, sauce, cheese, toppings, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, favorite.getUserEmail());
            stmt.setInt(2, favorite.getPizzaId());
            stmt.setString(3, favorite.getPizzaName());
            stmt.setString(4, favorite.getCrust());
            stmt.setString(5, favorite.getSauce());
            stmt.setString(6, favorite.getCheese());
            stmt.setString(7, favorite.getToppings());
            stmt.setDouble(8, favorite.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Favorite> getFavoritesByUser(String userEmail) {
        List<Favorite> favorites = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Favorite WHERE userEmail = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                favorites.add(new Favorite(
                        rs.getString("userEmail"),
                        rs.getInt("pizzaId"),
                        rs.getString("pizzaName"),
                        rs.getString("crust"),
                        rs.getString("sauce"),
                        rs.getString("cheese"),
                        rs.getString("toppings"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }

    public boolean removeFavorite(String userEmail, String pizzaName) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM Favorite WHERE userEmail = ? AND pizzaName = ?";
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
