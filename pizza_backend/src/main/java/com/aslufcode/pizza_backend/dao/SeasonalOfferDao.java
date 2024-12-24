package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.SeasonalOffer;
import com.aslufcode.pizza_backend.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SeasonalOfferDao {
    public List<SeasonalOffer> getAllOffers() {
        List<SeasonalOffer> offers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM SeasonalOffers";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                offers.add(new SeasonalOffer(
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
