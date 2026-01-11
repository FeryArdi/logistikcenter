package com.mycompany.gudang.model;

import com.mycompany.gudang.util.DB;
import java.sql.*;
import java.util.*;

public class AsetDAO {

    // =====================================================
    // 1. GET SEMUA ASET
    // =====================================================
    public List<Aset> getAll() {
        List<Aset> list = new ArrayList<>();
        String sql = "SELECT * FROM aset ORDER BY nama ASC";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aset a = new Aset();
                a.setId(rs.getInt("id"));
                a.setNama(rs.getString("nama"));
                a.setTipe(rs.getString("tipe"));     // barang / ruangan
                a.setLokasi(rs.getString("lokasi"));
                a.setStok(rs.getInt("stok"));
                list.add(a);
            }

        } catch (Exception e) {
            System.out.println("Error getAll Aset: " + e.getMessage());
        }

        return list;
    }

    // =====================================================
    // 2. GET ASET BERDASARKAN ID
    // =====================================================
    public Aset getById(int id) {
        String sql = "SELECT * FROM aset WHERE id=?";
        Aset a = null;

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Aset();
                a.setId(rs.getInt("id"));
                a.setNama(rs.getString("nama"));
                a.setTipe(rs.getString("tipe"));
                a.setLokasi(rs.getString("lokasi"));
                a.setStok(rs.getInt("stok"));
            }

        } catch (Exception e) {
            System.out.println("Error getById Aset: " + e.getMessage());
        }

        return a;
    }

    // =====================================================
    // 3. KURANGI STOK BARANG (SAAT APPROVE)
    // =====================================================
    public boolean kurangiStok(int aset_id, int jumlah) {
        String sql = "UPDATE aset SET stok = stok - ? WHERE id=? AND stok >= ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, jumlah);
            ps.setInt(2, aset_id);
            ps.setInt(3, jumlah);

            int affected = ps.executeUpdate();
            return affected > 0; // false jika stok tidak cukup

        } catch (Exception e) {
            System.out.println("Error kurangiStok: " + e.getMessage());
            return false;
        }
    }

    // =====================================================
    // 4. TAMBAH STOK BARANG (SAAT DIKEMBALIKAN)
    // =====================================================
    public void tambahStok(int aset_id, int jumlah) {
        String sql = "UPDATE aset SET stok = stok + ? WHERE id=?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, jumlah);
            ps.setInt(2, aset_id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error tambahStok: " + e.getMessage());
        }
    }

    // =====================================================
    // 5. TAMBAH ASET BARU (OPSIONAL - ADMIN)
    // =====================================================
    public void tambahAset(Aset a) {
        String sql = "INSERT INTO aset (nama, tipe, lokasi, stok) VALUES (?, ?, ?, ?)";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, a.getNama());
            ps.setString(2, a.getTipe());
            ps.setString(3, a.getLokasi());
            ps.setInt(4, a.getStok());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error tambahAset: " + e.getMessage());
        }
    }

    // =====================================================
    // 6. UPDATE DATA ASET (OPSIONAL)
    // =====================================================
    public void updateAset(Aset a) {
        String sql = "UPDATE aset SET nama=?, tipe=?, lokasi=?, stok=? WHERE id=?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, a.getNama());
            ps.setString(2, a.getTipe());
            ps.setString(3, a.getLokasi());
            ps.setInt(4, a.getStok());
            ps.setInt(5, a.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updateAset: " + e.getMessage());
        }
    }

    // =====================================================
    // 7. HAPUS ASET (OPSIONAL)
    // =====================================================
    public void deleteAset(int id) {
        String sql = "DELETE FROM aset WHERE id=?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error deleteAset: " + e.getMessage());
        }
    }
}
