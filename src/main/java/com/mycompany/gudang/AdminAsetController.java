package com.mycompany.gudang;

import com.mycompany.gudang.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-aset")
public class AdminAsetController extends HttpServlet {

    private final AsetDAO asetDAO = new AsetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // =============================
        // TAMPILKAN SEMUA ASET
        // =============================
        List<Aset> list = asetDAO.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/views/Admin/admin_barang.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // =============================
        // TAMBAH BARANG BARU
        // =============================
        if ("tambah".equals(action)) {

            Aset a = new Aset();
            a.setNama(req.getParameter("nama"));
            a.setTipe("barang");
            a.setLokasi(req.getParameter("lokasi"));
            a.setStok(Integer.parseInt(req.getParameter("stok")));

            asetDAO.tambahAset(a);

            resp.sendRedirect(req.getContextPath() + "/admin-aset");
            return;
        }

        // =============================
        // TAMBAH STOK BARANG
        // =============================
        if ("tambahStok".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));
            int jumlah = Integer.parseInt(req.getParameter("jumlah"));

            asetDAO.tambahStok(id, jumlah);

            resp.sendRedirect(req.getContextPath() + "/admin-aset");
            return;
        }
        // =============================
        // KURANGI STOK BARANG
        // =============================
        if ("kurangiStok".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            int jumlah = Integer.parseInt(req.getParameter("jumlah"));
            boolean sukses = asetDAO.kurangiStok(id, jumlah);

            if (!sukses) {
                resp.sendRedirect(req.getContextPath() + "/admin-aset?error=stok_kurang");
                return;
            }

            resp.sendRedirect(req.getContextPath() + "/admin-aset?msg=stok_kurang");
        }
    }
}
