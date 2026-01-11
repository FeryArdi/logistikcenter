package com.mycompany.gudang;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.mycompany.gudang.model.*;

@WebServlet("/admin-peminjaman")
public class AdminPeminjamanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ===============================
        // CEK LOGIN
        // ===============================
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        PeminjamanDAO pemDAO = new PeminjamanDAO();
        AsetDAO asetDAO = new AsetDAO();

        String action = req.getParameter("action");

        // ===============================
        // LOAD DATA ADMIN
        // ===============================
        if (action == null) {
            System.out.println("ADMIN LOAD: getAllDetail()");
            req.setAttribute("listAll", pemDAO.getAllDetail());
            req.setAttribute("listKembali", pemDAO.getDisetujui());
            req.getRequestDispatcher("/views/Admin/admin_peminjaman_pengajuan.jsp")
                .forward(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Peminjaman p = pemDAO.getById(id);
            if (p == null) {
                resp.sendRedirect("admin-peminjaman?msg=data_tidak_ditemukan");
                return;
            }

            Aset aset = asetDAO.getById(p.getAset_id());
            if (aset == null) {
                resp.sendRedirect("admin-peminjaman?msg=aset_tidak_ditemukan");
                return;
            }

            // ===============================
            // APPROVE PEMINJAMAN
            // ===============================
            if ("approve".equals(action)) {

                if ("ruangan".equals(aset.getTipe())) {
                    boolean bentrok = pemDAO.cekBentrokRuanganAdmin(
                            p.getId(), aset.getId(),
                            p.getTgl_mulai(), p.getTgl_selesai()
                    );
                    if (bentrok) {
                        resp.sendRedirect("admin-peminjaman?msg=ruangan_bentrok");
                        return;
                    }
                }

                if ("barang".equals(aset.getTipe())) {
                    if (p.getJumlah() > aset.getStok()) {
                        resp.sendRedirect("admin-peminjaman?msg=stok_tidak_cukup");
                        return;
                    }
                    asetDAO.kurangiStok(aset.getId(), p.getJumlah());
                }

                pemDAO.updateStatus(id, "disetujui");
                resp.sendRedirect("admin-peminjaman?msg=disetujui");
                return;
            }

            // ===============================
            // REJECT PEMINJAMAN
            // ===============================
            if ("reject".equals(action)) {
                pemDAO.updateStatus(id, "ditolak");
                resp.sendRedirect("admin-peminjaman?msg=ditolak");
                return;
            }

            // ===============================
            // VERIFIKASI PENGEMBALIAN
            // ===============================
if ("verifikasi_kembali".equals(action)) {
    System.out.println("➡ VERIFIKASI PENGEMBALIAN ID=" + id);
    if ("barang".equals(aset.getTipe())) {
        asetDAO.tambahStok(aset.getId(), p.getJumlah());
    }
    pemDAO.updateStatus(id, "selesai");
    resp.sendRedirect("admin-peminjaman");
    return;
}

            // TOLAK PENGEMBALIAN
            // ===============================
            if ("tolak_kembali".equals(action)) {
                pemDAO.updateStatus(id, "disetujui");
                resp.sendRedirect("admin-peminjaman?msg=pengembalian_ditolak");
                return;
            }
            resp.sendRedirect("admin-peminjaman?msg=aksi_tidak_valid");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("admin-peminjaman?msg=error");
        }
    }
}
