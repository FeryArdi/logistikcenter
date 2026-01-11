<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.gudang.model.Aset"%>
<%@page import="java.util.List"%>

<%
    List<Aset> list = (List<Aset>) request.getAttribute("daftarAset");
    int no = 1;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin | Daftar Aset</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<!-- SIDEBAR -->
<%@ include file="layout/sidebar.jsp" %>

<div class="content">

    <div class="box">
        <h2>Daftar Aset</h2>

        <table class="riwayat-table">
            <tr>
                <th>No</th>
                <th>Nama Aset</th>
                <th>Tipe</th>
                <th>Stok</th>
                <th>Lokasi</th>
            </tr>

            <!-- ================= BARANG ================= -->
            <% if (list != null) {
                for (Aset a : list) {
                    if (!"barang".equalsIgnoreCase(a.getTipe())) continue;
            %>
            <tr>
                <td><%= no++ %></td>
                <td><%= a.getNama() %></td>
                <td>Barang</td>
                <td><%= a.getStok() %></td>
                <td><%= a.getLokasi() %></td>
            </tr>
            <% }} %>

            <!-- ================= RUANGAN ================= -->
            <% if (list != null) {
                for (Aset a : list) {
                    if (!"ruangan".equalsIgnoreCase(a.getTipe())) continue;
            %>
            <tr>
                <td><%= no++ %></td>
                <td><%= a.getNama() %></td>
                <td>Ruangan</td>
                <td><%= a.getStok() %></td>
                <td><%= a.getLokasi() %></td>
            </tr>
            <% }} %>

            <% if (list == null || list.isEmpty()) { %>
            <tr>
                <td colspan="5" style="text-align:center;">
                    Belum ada data aset
                </td>
            </tr>
            <% } %>
        </table>

    </div>

</div>

</body>
</html>
