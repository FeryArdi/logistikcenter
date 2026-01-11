package com.mycompany.gudang;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.mycompany.gudang.model.*;

@WebServlet("/peminjaman")
public class PeminjamanController extends HttpServlet {

    // =================================================
    // GET → RIWAYAT PEMINJAMAN USER
    // =================================================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        PeminjamanDAO pemDAO = new PeminjamanDAO();

        req.setAttribute("list", pemDAO.getByUser(user.getId()));
        req.getRequestDispatcher("/views/peminjaman.jsp")
           .forward(req, resp);
    }

    // =================================================
    // POST → AJUKAN PEMINJAMAN BARU
    // =================================================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        try {
            int aset_id = Integer.parseInt(req.getParameter("aset_id"));
            String mulai = req.getParameter("tgl_mulai");
            String selesai = req.getParameter("tgl_selesai");
            int unit_id  = Integer.parseInt(req.getParameter("unit_id"));            String namaUnit = req.getParameter("nama_unit");
            String namaKegiatan = req.getParameter("nama_kegiatan");
            String noHp = req.getParameter("no_hp");

            int jumlah = 1;
            if (req.getParameter("jumlah") != null && !req.getParameter("jumlah").isEmpty()) {
                jumlah = Integer.parseInt(req.getParameter("jumlah"));
            }

            AsetDAO asetDAO = new AsetDAO();
            Aset aset = asetDAO.getById(aset_id);

            if (aset == null) {
                req.setAttribute("error", "Aset tidak ditemukan!");
                req.getRequestDispatcher("/views/peminjaman.jsp").forward(req, resp);
                return;
            }

            PeminjamanDAO pemDAO = new PeminjamanDAO();

            // CEK BENTROK RUANGAN
            if ("ruangan".equalsIgnoreCase(aset.getTipe())) {
                boolean bentrok = pemDAO.cekBentrokRuanganUser(
                        aset_id, mulai, selesai
                );
                if (bentrok) {
                    req.setAttribute("error",
                        "Ruangan sudah dipinjam pada tanggal tersebut.");
                    req.getRequestDispatcher("/views/peminjaman.jsp").forward(req, resp);
                    return;
                }
            }

            // CEK STOK BARANG
            if ("barang".equalsIgnoreCase(aset.getTipe())) {
                if (jumlah > aset.getStok()) {
                    req.setAttribute("error",
                        "Stok tidak cukup. Stok tersedia: " + aset.getStok());
                    req.getRequestDispatcher("/views/peminjaman.jsp").forward(req, resp);
                    return;
                }
            }

            Peminjaman p = new Peminjaman();
            p.setUser_id(user.getId());
            p.setAset_id(aset_id);
            p.setTgl_mulai(mulai);
            p.setTgl_selesai(selesai);
            p.setJumlah(jumlah);
            p.setStatus("ajukan");
            p.setUnit_id(unit_id);  
            p.setNama_unit(namaUnit);
            p.setNama_kegiatan(namaKegiatan);
            p.setNo_hp(noHp);

            pemDAO.tambah(p);

            resp.sendRedirect(req.getContextPath() + "/peminjaman");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Terjadi kesalahan sistem.");
            req.getRequestDispatcher("/views/peminjaman.jsp").forward(req, resp);
        }
    }
}