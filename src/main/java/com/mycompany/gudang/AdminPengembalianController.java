package com.mycompany.gudang;

import com.mycompany.gudang.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-verifikasi")
public class AdminPengembalianController extends HttpServlet {

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
        AsetDAO asetDAO = new AsetDAO();

        String action = req.getParameter("action");

        // ======================
        // TAMPILKAN YANG DISETUJUI
        // ======================
        if (action == null) {
            System.out.println("ADMIN LOAD: getAllDetail()");
            req.setAttribute("listKembali", pemDAO.getDisetujuiDetail());
            req.getRequestDispatcher(
                "/views/Admin/admin_verifikasi.jsp"
            ).forward(req, resp);
            return;
        }

        // ======================
        // VERIFIKASI
        // ======================
        int id = Integer.parseInt(req.getParameter("id"));
        Peminjaman p = pemDAO.getById(id);
        Aset aset = asetDAO.getById(p.getAset_id());

        if ("verifikasi".equals(action)) {

            if ("barang".equals(aset.getTipe())) {
                asetDAO.tambahStok(aset.getId(), p.getJumlah());
            }

            pemDAO.updateStatus(id, "selesai");
            resp.sendRedirect("admin-verifikasi");
        }
    }
}
