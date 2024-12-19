package com.aslufcode.pizza_backend.dao;

import com.aslufcode.pizza_backend.models.RegisterRequest;
import com.aslufcode.pizza_backend.models.User;
import com.aslufcode.pizza_backend.utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDAO {
    public User getUserById(int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM User WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("mobile"),
                    rs.getInt("loyaltyPoints")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerUser(RegisterRequest user) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO User (name, email, password, mobile) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setString(4, user.getMobile());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User validateLogin(String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM User WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                return new User(
                    rs.getInt("userId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    null,
                    rs.getString("mobile"),
                    rs.getInt("loyaltyPoints")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}