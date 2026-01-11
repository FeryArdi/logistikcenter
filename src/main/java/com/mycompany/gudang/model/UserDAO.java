package com.mycompany.gudang.model;

import com.mycompany.gudang.util.DB;
import java.sql.*;

public class UserDAO {

    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try {
            Connection conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));   // ✅ FIX DI SINI
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));

                System.out.println("LOGIN BERHASIL: " + u.getEmail());
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
