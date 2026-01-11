package com.mycompany.gudang;

import com.mycompany.gudang.model.Peminjaman;
import com.mycompany.gudang.model.PeminjamanDAO;
import com.mycompany.gudang.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/pengembalian")
public class PengembalianController extends HttpServlet {

    // =====================
    // GET → TAMPILKAN DATA
    // =====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        PeminjamanDAO dao = new PeminjamanDAO();

        // 🔥 HANYA YANG SUDAH DISETUJUI
        List<Peminjaman> list =
                dao.getDisetujuiByUser(user.getId());

        req.setAttribute("listPeminjaman", list);
        req.getRequestDispatcher("/views/pengembalian.jsp")
           .forward(req, resp);
    }

    // =====================
    // POST → AJUKAN PENGEMBALIAN
    // =====================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    
    System.out.println("========== DO POST PENGEMBALIAN TERPANGGIL ==========");
    
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
        resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(req.getParameter("id"));
    } catch (NumberFormatException e) {
        resp.sendRedirect("pengembalian?msg=id_tidak_valid");
        return;
    }

    PeminjamanDAO dao = new PeminjamanDAO();
    Peminjaman p = dao.getById(id);

    if (p == null || p.getUser_id() != user.getId()) {
        resp.sendRedirect("pengembalian?msg=akses_ditolak");
        return;
    }

    dao.updateStatus(id, "proses_pengembalian");

    resp.sendRedirect("pengembalian?msg=menunggu_verifikasi");
}
}
