package com.mycompany.gudang;

import com.mycompany.gudang.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-riwayat")
public class AdminRiwayatController extends HttpServlet {

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
        req.setAttribute("listAll", pemDAO.getRiwayatDetail());
        req.getRequestDispatcher(
            "/views/Admin/admin_riwayat.jsp"
        ).forward(req, resp);
    }
}
