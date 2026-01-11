/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gudang.model;

/**
 *
 * @author FERY
 */

import com.mycompany.gudang.util.DB;
import java.sql.*;

public class PengembalianDAO {

    public void tambah(Pengembalian p) {
        String sql = "INSERT INTO pengembalian VALUES(NULL, ?, ?, ?)";

        try {
            Connection c = DB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, p.getPeminjaman_id());
            ps.setString(2, p.getTgl_kembali());
            ps.setString(3, p.getKondisi());
            ps.execute();

        } catch (Exception e) {
            System.out.println("Error Tambah Pengembalian: " + e.getMessage());
        }
    }
    public void kembalikan(int id) {
    String sql = "UPDATE peminjaman " +
                 "SET status='dikembalikan', tgl_kembali=NOW() " +
                 "WHERE id=?";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

