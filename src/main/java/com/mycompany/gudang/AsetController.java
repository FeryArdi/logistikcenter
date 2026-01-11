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
import java.util.List;

import com.mycompany.gudang.model.Aset;
import com.mycompany.gudang.model.AsetDAO;

@WebServlet("/aset")
public class AsetController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AsetDAO dao = new AsetDAO();
        List<Aset> list = dao.getAll();

        req.setAttribute("daftarAset", list);
        req.getRequestDispatcher("views/aset.jsp").forward(req, resp);
    }
}
