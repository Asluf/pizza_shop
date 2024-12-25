package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.Pizza;
import com.aslufcode.pizza_backend.utils.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PizzaDao {
    public static List<Pizza> getAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Pizza WHERE type = 'normal'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pizzas.add(new Pizza(
                        rs.getInt("pizzaId"),
                        rs.getString("name"),
                        rs.getString("crust"),
                        rs.getString("sauce"),
                        rs.getString("cheese"),
                        rs.getString("toppings"),
                        rs.getDouble("price"),
                        rs.getDouble("discount"),
                        rs.getDouble("netPrice")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzas;
    }

    public static List<Pizza> getAllOffers() {
        List<Pizza> offers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Pizza WHERE type = 'offer'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                offers.add(new Pizza(
                    rs.getInt("pizzaId"),
                    rs.getString("name"),
                    rs.getString("crust"),
                    rs.getString("sauce"),
                    rs.getString("cheese"),
                    rs.getString("toppings"),
                    rs.getDouble("price"),
                    rs.getDouble("discount"),
                    rs.getDouble("netPrice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }
}
