/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gudang;

/**
 *
 * @author FERY
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.mycompany.gudang.model.PeminjamanDAO;

@WebServlet("/ubahStatus")
public class UbahStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        PeminjamanDAO dao = new PeminjamanDAO();
        dao.updateStatus(id, status);

        resp.sendRedirect("views/admin_peminjaman.jsp");
    }
}
