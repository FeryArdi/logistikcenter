package com.mycompany.gudang.model;

import com.mycompany.gudang.util.DB;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;

public class PeminjamanDAO {

    public void tambah(Peminjaman p) {

        String sql = "INSERT INTO peminjaman " +
        "(user_id, unit_id, aset_id, tgl_mulai, tgl_selesai, status, jumlah, nama_unit, nama_kegiatan, no_hp) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, p.getUser_id());
        ps.setInt(2, p.getUnit_id());      
        ps.setInt(3, p.getAset_id());    
        ps.setDate(4, java.sql.Date.valueOf(p.getTgl_mulai()));
        ps.setDate(5, java.sql.Date.valueOf(p.getTgl_selesai()));
        ps.setString(6, p.getStatus());
        ps.setInt(7, p.getJumlah());
        ps.setString(8, p.getNama_unit());
        ps.setString(9, p.getNama_kegiatan());
        ps.setString(10, p.getNo_hp());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Tambah Peminjaman Error: " + e.getMessage());
        }
    }

    public List<Peminjaman> getByDate(String tanggal) {
        List<Peminjaman> list = new ArrayList<>();

        String sql = "SELECT * FROM peminjaman "
                   + "WHERE status='disetujui' "
                   + "AND tgl_mulai <= ? AND tgl_selesai >= ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tanggal);
            ps.setString(2, tanggal);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            System.out.println("getByDate Error: " + e.getMessage());
        }

        return list;
    }
    public List<Peminjaman> getAll() {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, a.nama AS nama_aset, u.nama_unit, usr.name AS nama_user " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN user usr ON p.user_id = usr.id " +
        "WHERE p.status = 'ajukan' " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = map(rs);              
            p.setNama_aset(rs.getString("nama_aset")); 
            p.setNama_unit(rs.getString("nama_unit"));
            p.setNama_user(rs.getString("nama_user"));
            list.add(p);
        }

    } catch (Exception e) {
        System.out.println("getAll Error: " + e.getMessage());
        e.printStackTrace();
    }

    return list;
}

    public Peminjaman getById(int id) {
        String sql = "SELECT * FROM peminjaman WHERE id=?";
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);

        } catch (Exception e) {
            System.out.println("getById Error: " + e.getMessage());
        }
        return null;
    }


    public boolean cekBentrokRuanganUser(int aset_id, String mulai, String selesai) {

        String sql = "SELECT 1 FROM peminjaman "
                   + "WHERE aset_id=? AND status='disetujui' "
                   + "AND NOT (tgl_selesai < ? OR tgl_mulai > ?)";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, aset_id);
            ps.setString(2, mulai);
            ps.setString(3, selesai);

            return ps.executeQuery().next();

        } catch (Exception e) {
            return true;
        }
    }

    public boolean cekBentrokRuanganAdmin(
            int peminjaman_id, int aset_id, String mulai, String selesai) {

        String sql = "SELECT 1 FROM peminjaman "
                   + "WHERE aset_id=? AND id<>? AND status='disetujui' "
                   + "AND NOT (tgl_selesai < ? OR tgl_mulai > ?)";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, aset_id);
            ps.setInt(2, peminjaman_id);
            ps.setString(3, mulai);
            ps.setString(4, selesai);

            return ps.executeQuery().next();

        } catch (Exception e) {
            return true;
        }
    }

    public void updateStatus(int id, String status) {

    String sql = "UPDATE peminjaman SET status=? WHERE id=?";

    try (Connection c = DB.getConnection()) {

        if (c == null) {
            System.out.println(" KONEKSI DB NULL");
            return;
        }

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, status);
        ps.setInt(2, id);

        int affected = ps.executeUpdate();

        System.out.println(" UPDATE STATUS ID=" + id +
                " → " + status +
                " | rows=" + affected);

    } catch (Exception e) {
        System.out.println(" updateStatus ERROR");
        e.printStackTrace();
    }
}


    private Peminjaman map(ResultSet rs) throws SQLException {
        Peminjaman p = new Peminjaman();
        p.setId(rs.getInt("id"));
        p.setUser_id(rs.getInt("user_id"));
        p.setAset_id(rs.getInt("aset_id"));
        p.setTgl_mulai(rs.getString("tgl_mulai"));
        p.setTgl_selesai(rs.getString("tgl_selesai"));
        p.setStatus(rs.getString("status"));
        p.setJumlah(rs.getInt("jumlah"));
//        p.setNama_unit(rs.getString("nama_unit"));
        p.setNama_kegiatan(rs.getString("nama_kegiatan"));
        p.setNo_hp(rs.getString("no_hp"));
        p.setUnit_id(rs.getInt("unit_id"));
        p.setNama_unit(rs.getString("nama_unit")); 
        return p;
    }
    
   public List<Peminjaman> getKalender(String tanggal) {

        List<Peminjaman> list = new ArrayList<>();
        String sql;

        boolean filter = !(tanggal == null || tanggal.trim().isEmpty());

        sql = "SELECT p.nama_unit, p.nama_kegiatan, a.nama AS nama_aset, " +
              "p.tgl_mulai, p.tgl_selesai " +
              "FROM peminjaman p " +
              "JOIN aset a ON p.aset_id = a.id " +
              "WHERE p.status = 'disetujui' ";

        if (filter) {
            sql += "AND p.tgl_mulai <= ? AND p.tgl_selesai >= ? ";
        }

        sql += "ORDER BY p.tgl_mulai";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            if (filter) {
                ps.setDate(1, java.sql.Date.valueOf(tanggal));
                ps.setDate(2, java.sql.Date.valueOf(tanggal));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Peminjaman p = new Peminjaman();
                p.setNama_unit(rs.getString("nama_unit"));
                p.setNama_kegiatan(rs.getString("nama_kegiatan"));
                p.setNama_aset(rs.getString("nama_aset"));
                p.setTgl_mulai(rs.getString("tgl_mulai"));
                p.setTgl_selesai(rs.getString("tgl_selesai"));
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("Kalender Error: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

  public List<Peminjaman> getByUser(int userId) {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, " +
        "       a.nama AS nama_aset, " +
        "       u.nama_unit AS nama_unit_join " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "WHERE p.user_id = ? " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Peminjaman p = new Peminjaman();

            p.setId(rs.getInt("id"));
            p.setUser_id(rs.getInt("user_id"));
            p.setUnit_id(rs.getInt("unit_id"));
            p.setAset_id(rs.getInt("aset_id"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setJumlah(rs.getInt("jumlah"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));

            String namaUnit = rs.getString("nama_unit_join");
            if (namaUnit == null) {
                namaUnit = "-";
            }
            p.setNama_unit(namaUnit);

            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


   public List<Peminjaman> getDisetujuiByUser(int userId) {
     List<Peminjaman> list = new ArrayList<>();

    String sql = "SELECT p.*, a.nama AS nama_aset " +
                 "FROM peminjaman p " +
                 "JOIN aset a ON p.aset_id = a.id " +
                 "WHERE p.status = 'disetujui' " +
                 "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = map(rs);
            p.setNama_aset(rs.getString("nama_aset"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
   
public List<Peminjaman> getDikembalikan() {

    List<Peminjaman> list = new ArrayList<>();

    String sql = "SELECT p.*, a.nama AS nama_aset " +
                 "FROM peminjaman p " +
                 "JOIN aset a ON p.aset_id = a.id " +
                 "WHERE p.status = 'dikembalikan' " +
                 "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = map(rs);
            p.setNama_aset(rs.getString("nama_aset"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public List<Peminjaman> getByStatus(String status) {
    List<Peminjaman> list = new ArrayList<>();

    String sql = "SELECT p.*, a.nama AS nama_aset " +
                 "FROM peminjaman p " +
                 "JOIN aset a ON p.aset_id = a.id " +
                 "WHERE p.status = ?";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, status);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Peminjaman p = map(rs);
            p.setNama_aset(rs.getString("nama_aset"));
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public List<Peminjaman> getDisetujui() {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, a.nama AS nama_aset, u.nama_unit, usr.name AS nama_user " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN users usr ON p.user_id = usr.id " +
        "WHERE p.status = 'disetujui' " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = map(rs);
            p.setNama_aset(rs.getString("nama_aset"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getSelesaiByUser(int userId) {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, " +
        "       a.nama AS nama_aset, " +
        "       u.nama_unit AS nama_unit_join " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "WHERE p.user_id = ? " +
        "AND p.status = 'selesai' " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Peminjaman p = new Peminjaman();

            p.setId(rs.getInt("id"));
            p.setUser_id(rs.getInt("user_id"));
            p.setUnit_id(rs.getInt("unit_id"));
            p.setAset_id(rs.getInt("aset_id"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setJumlah(rs.getInt("jumlah"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));

            String namaUnit = rs.getString("nama_unit_join");
            if (namaUnit == null) {
                namaUnit = "-";
            }
            p.setNama_unit(namaUnit);

            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getAllDetail() {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, " +
        "p.no_hp, " +   
        "a.nama AS nama_aset, " +
        "usr.name AS nama_user, " +
        "CASE " +
        "  WHEN p.unit_id IS NOT NULL THEN u.nama_unit " +
        "  ELSE '-' " +
        "END AS unit_display " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN users usr ON p.user_id = usr.id " +
        "WHERE p.status = 'ajukan' " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = new Peminjaman();
            p.setId(rs.getInt("id"));
            p.setNama_user(rs.getString("nama_user"));
            p.setNama_unit(rs.getString("unit_display")); 
            p.setNo_hp(rs.getString("no_hp"));          
            p.setNama_kegiatan(rs.getString("nama_kegiatan"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setJumlah(rs.getInt("jumlah"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getDisetujuiDetail() {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, " +
        "p.no_hp, " +   
        "a.nama AS nama_aset, " +
        "usr.name AS nama_user, " +
        "CASE " +
        "  WHEN p.unit_id IS NOT NULL THEN u.nama_unit " +
        "  ELSE '-' " +
        "END AS unit_display " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN users usr ON p.user_id = usr.id " +
        "WHERE p.status = 'disetujui' " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = new Peminjaman();
            p.setId(rs.getInt("id"));
            p.setNama_user(rs.getString("nama_user"));
            p.setNama_unit(rs.getString("unit_display")); // 🔥 FIX
            p.setNo_hp(rs.getString("no_hp"));
            p.setNama_kegiatan(rs.getString("nama_kegiatan"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setJumlah(rs.getInt("jumlah"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getRiwayatDetail() {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, " +
        "p.no_hp, " +   
        "a.nama AS nama_aset, " +
        "usr.name AS nama_user, " +
        "CASE " +
        "  WHEN p.unit_id IS NOT NULL THEN u.nama_unit " +
        "  ELSE '-' " +
        "END AS unit_display " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN users usr ON p.user_id = usr.id " +
        "ORDER BY p.id DESC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Peminjaman p = new Peminjaman();
            p.setId(rs.getInt("id"));
            p.setNama_user(rs.getString("nama_user"));
            p.setNama_unit(rs.getString("unit_display")); 
            p.setNo_hp(rs.getString("no_hp"));
            p.setNama_kegiatan(rs.getString("nama_kegiatan"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setJumlah(rs.getInt("jumlah"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getPeminjamanRuanganByBulan(int tahun, int bulan) {
    List<Peminjaman> list = new ArrayList<>();

    LocalDate start = LocalDate.of(tahun, bulan, 1);
    LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

    System.out.println("DAO HIT: getPeminjamanRuanganByBulan");
    System.out.println("KALENDER RANGE: " + start + " s/d " + end);

    String sql =
        "SELECT p.*, " +
        "a.nama AS nama_aset, " +
        "usr.name AS nama_user, " +
        "CASE " +
        "  WHEN p.unit_id IS NOT NULL THEN u.nama_unit " +
        "  ELSE '-' " +
        "END AS unit_display " +
        "FROM peminjaman p " +
        "LEFT JOIN aset a ON p.aset_id = a.id AND a.tipe = 'ruangan' " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "JOIN users usr ON p.user_id = usr.id " +
        "WHERE p.status IN ('disetujui','selesai') " +
        "AND p.tgl_mulai BETWEEN ? AND ? " +
        "AND a.id IS NOT NULL " +
        "ORDER BY p.tgl_mulai ASC";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setDate(1, java.sql.Date.valueOf(start));
        ps.setDate(2, java.sql.Date.valueOf(end));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Peminjaman p = new Peminjaman();
            p.setId(rs.getInt("id"));
            p.setNama_unit(rs.getString("unit_display"));
            p.setNama_kegiatan(rs.getString("nama_kegiatan"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            p.setStatus(rs.getString("status"));
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<String> getTanggalRuanganDipinjam(int tahun, int bulan) {
    List<String> list = new ArrayList<>();

    String sql =
        "SELECT DISTINCT p.tgl_mulai " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "WHERE a.tipe = 'ruangan' " +
        "AND p.status IN ('disetujui','selesai') " +
        "AND MONTH(p.tgl_mulai) = ? " +
        "AND YEAR(p.tgl_mulai) = ?";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, bulan);
        ps.setInt(2, tahun);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("tgl_mulai")); // yyyy-MM-dd
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<Peminjaman> getPeminjamanRuanganByTanggal(String tanggal) {
    List<Peminjaman> list = new ArrayList<>();

    String sql =
        "SELECT p.*, a.nama AS nama_aset, " +
        "CASE WHEN p.unit_id IS NOT NULL THEN u.nama_unit ELSE '-' END AS unit_display " +
        "FROM peminjaman p " +
        "JOIN aset a ON p.aset_id = a.id " +
        "LEFT JOIN unit_peminjam u ON p.unit_id = u.id " +
        "WHERE a.tipe = 'ruangan' " +
        "AND p.status IN ('disetujui','selesai') " +
        "AND p.tgl_mulai = ? " +
        "ORDER BY p.tgl_mulai";

    try (Connection c = DB.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, tanggal);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Peminjaman p = new Peminjaman();
            p.setNama_unit(rs.getString("unit_display"));
            p.setNama_kegiatan(rs.getString("nama_kegiatan"));
            p.setNama_aset(rs.getString("nama_aset"));
            p.setTgl_mulai(rs.getString("tgl_mulai"));
            p.setTgl_selesai(rs.getString("tgl_selesai"));
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

}
