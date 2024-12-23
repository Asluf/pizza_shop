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
            String query = "SELECT * FROM Pizza";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pizzas.add(new Pizza(
                    rs.getString("name"),
                    rs.getString("crust"),
                    rs.getString("sauce"),
                    rs.getString("cheese"),
                    rs.getString("toppings"),
                    rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzas;
    }
}
