<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.Aset"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manajemen Barang</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

<!-- SIDEBAR ADMIN -->
<%@ include file="../layout/sidebar_admin.jsp" %>

<!-- KONTEN -->
<div class="content">

    <h2>Manajemen Barang</h2>

    <table class="riwayat-table">
        <tr>
            <th>No</th>
            <th>Nama</th>
            <th>Stok</th>
            <th>Lokasi</th>
            <th>Aksi</th>
        </tr>

<%
List<Aset> list = (List<Aset>) request.getAttribute("list");
int no = 1;

if (list != null && !list.isEmpty()) {
    for (Aset a : list) {
        if (!"barang".equals(a.getTipe())) continue;
%>
        <tr>
            <td><%= no++ %></td>
            <td><%= a.getNama() %></td>
            <td><%= a.getStok() %></td>
            <td><%= a.getLokasi() %></td>
            <td>

                <!-- TAMBAH STOK -->
                <form action="<%=request.getContextPath()%>/admin-aset"
                      method="post" style="display:inline;">
                    <input type="hidden" name="action" value="tambahStok">
                    <input type="hidden" name="id" value="<%=a.getId()%>">
                    <input type="number" name="jumlah" min="1" required class="stok-input">
                    <button class="btn-approve stok-btn">+</button>
                </form>

                <!-- KURANGI STOK -->
                <form action="<%=request.getContextPath()%>/admin-aset"
                      method="post" style="display:inline;">
                    <input type="hidden" name="action" value="kurangiStok">
                    <input type="hidden" name="id" value="<%=a.getId()%>">
                    <input type="number" name="jumlah" min="1" required class="stok-input">
                    <button class="btn-reject stok-btn">−</button>
                </form>

            </td>
        </tr>
<%
    }
} else {
%>
        <tr>
            <td colspan="5" style="text-align:center;">
                Tidak ada data barang
            </td>
        </tr>
<%
}
%>
    </table>

</div>
</body>
</html>
