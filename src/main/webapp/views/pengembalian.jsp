<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.*"%>

<%
    // =====================
    // CEK LOGIN USER
    // =====================
    User userLogin = (User) session.getAttribute("user");
    if (userLogin == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    // =====================
    // AMBIL DATA PENGEMBALIAN (STATUS: selesai)
    // =====================
    PeminjamanDAO pemDAO = new PeminjamanDAO();
    List<Peminjaman> selesai = pemDAO.getSelesaiByUser(userLogin.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Pengembalian Aset</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<!-- SIDEBAR -->
<%@ include file="layout/sidebar.jsp" %>

<div class="content">

    <h2>Riwayat Aset yang Dikembalikan</h2>

    <table class="riwayat-table">
        <tr>
            <th>No</th>
            <th>Unit / Ormawa</th>
            <th>Nama Aset</th>
            <th>Jumlah</th>
            <th>Tanggal Peminjaman</th>
            <th>Status</th>
        </tr>

<%
    int no = 1;

    if (selesai != null && !selesai.isEmpty()) {
        for (Peminjaman p : selesai) {
%>
        <tr>
            <td><%= no++ %></td>
            <td><%= p.getNama_unit() %></td>
            <td><%= p.getNama_aset() %></td>
            <td><%= p.getJumlah() %></td>
            <td><%= p.getTgl_mulai() %> s/d <%= p.getTgl_selesai() %></td>
            <td>
                <span class="status selesai">selesai</span>
            </td>
        </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="6" style="text-align:center;">
                Belum ada aset yang dikembalikan
            </td>
        </tr>
<%
    }
%>
    </table>

</div>

</body>
</html>
