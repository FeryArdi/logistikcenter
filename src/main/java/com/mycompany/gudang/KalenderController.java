package com.mycompany.gudang;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

import com.mycompany.gudang.model.*;

@WebServlet("/kalender")
public class KalenderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int bulan;
        int tahun;

        try {
            bulan = Integer.parseInt(req.getParameter("bulan"));
            tahun = Integer.parseInt(req.getParameter("tahun"));
        } catch (Exception e) {
            LocalDate now = LocalDate.now();
            bulan = now.getMonthValue();
            tahun = now.getYear();
        }

        // 🔹 Ambil tanggal yang diklik (boleh null)
        String tanggalDipilih = req.getParameter("tanggal");

        PeminjamanDAO dao = new PeminjamanDAO();

        // 🔹 Untuk penanda kalender
        List<String> tanggalDipinjam =
            dao.getTanggalRuanganDipinjam(tahun, bulan);

        // 🔹 Untuk tabel detail
        List<Peminjaman> list;
        if (tanggalDipilih != null && !tanggalDipilih.isEmpty()) {
            list = dao.getPeminjamanRuanganByTanggal(tanggalDipilih);
        } else {
            list = dao.getPeminjamanRuanganByBulan(tahun, bulan);
        }

        // 🔹 Kirim ke JSP
        req.setAttribute("bulan", bulan);
        req.setAttribute("tahun", tahun);
        req.setAttribute("tanggalDipinjam", tanggalDipinjam);
        req.setAttribute("list", list);
        req.setAttribute("tanggalDipilih", tanggalDipilih);

        req.getRequestDispatcher("/views/kalender_admin.jsp")
           .forward(req, resp);
    }
}
