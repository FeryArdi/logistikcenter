package com.mycompany.gudang.model;

import com.mycompany.gudang.util.DB;
import java.sql.*;
import java.util.*;

public class UnitPeminjamDAO {

    public List<UnitPeminjam> getAll() {
        List<UnitPeminjam> list = new ArrayList<>();
        String sql = "SELECT * FROM unit_peminjam ORDER BY jenis, nama_unit";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UnitPeminjam u = new UnitPeminjam();
                u.setId(rs.getInt("id"));
                u.setNama_unit(rs.getString("nama_unit"));
                u.setJenis(rs.getString("jenis"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
