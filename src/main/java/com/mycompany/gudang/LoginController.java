package com.mycompany.gudang;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.mycompany.gudang.model.User;
import com.mycompany.gudang.model.UserDAO;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // ==============================
            // ADMIN
            // ==============================
            if ("admin".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin-dashboard");
            }
            // ==============================
            // USER / MAHASISWA
            // Dashboard = Daftar Aset
            // ==============================
            else {
                resp.sendRedirect(req.getContextPath() + "/aset");
            }

        } else {
            req.setAttribute("error", "Email atau Password salah!");
            req.getRequestDispatcher("views/login.jsp")
               .forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("views/login.jsp");
    }
}

