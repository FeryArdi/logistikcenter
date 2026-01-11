<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.Peminjaman"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Riwayat Peminjaman</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<%@ include file="../layout/sidebar_admin.jsp" %>

<div class="content">

<h2>Riwayat Peminjaman</h2>
<p>Data peminjaman yang telah selesai atau ditolak.</p>

<table class="riwayat-table">
<tr>
    <th>No</th>
    <th>Unit / Ormawa</th>
    <th>Aset</th>
    <th>Jumlah</th>
    <th>Tanggal</th>
    <th>Status</th>
    <th>Aksi</th>
</tr>

<%
List<Peminjaman> list =
    (List<Peminjaman>) request.getAttribute("listAll");

int no = 1;
boolean ada = false;

if (list != null) {
    for (Peminjaman p : list) {
        if (!"selesai".equals(p.getStatus()) &&
            !"ditolak".equals(p.getStatus())) continue;
        ada = true;
%>
<tr>
    <td><%= no++ %></td>
    <td><%= p.getNama_unit() %></td>
    <td><%= p.getNama_aset() %></td>
    <td><%= p.getJumlah() %></td>
    <td><%= p.getTgl_mulai() %> s/d <%= p.getTgl_selesai() %></td>
    <td>
        <span class="status <%= p.getStatus() %>">
            <%= p.getStatus() %>
        </span>
    </td>
    <td>
        <button type="button" class="btn-info"
            onclick="bukaDetail(this)"
            data-user="<%=p.getNama_user()%>"
            data-unit="<%=p.getNama_unit()%>"
            data-kegiatan="<%=p.getNama_kegiatan()%>"
            data-aset="<%=p.getNama_aset()%>"
            data-jumlah="<%=p.getJumlah()%>"
            data-nohp="<%=p.getNo_hp()%>"
            data-tanggal="<%=p.getTgl_mulai()%> s/d <%=p.getTgl_selesai()%>">
            ℹ️️
        </button>
    </td>
</tr>
<%
    }
}
%>

<% if (!ada) { %>
<tr>
    <td colspan="6" align="center">Belum ada riwayat peminjaman</td>
</tr>
<% } %>

</table>
<!-- ================= MODAL DETAIL ================= -->
<div id="modalDetail" class="modal">
    <div class="modal-content">
        <span class="close" onclick="tutupModal()">&times;</span>
        <h3>Detail Peminjaman</h3>
        <table class="detail-table">
            <tr><td>Nama</td><td id="d_user"></td></tr>
            <tr><td>Unit</td><td id="d_unit"></td></tr>
            <tr><td>Kegiatan</td><td id="d_kegiatan"></td></tr>
            <tr><td>Aset</td><td id="d_aset"></td></tr>
            <tr><td>Jumlah</td><td id="d_jumlah"></td></tr>
            <tr><td>No HP</td><td id="d_nohp"></td></tr>
            <tr><td>Tanggal</td><td id="d_tanggal"></td></tr>
        </table>
    </div>
</div>
</div>
<script>
function bukaDetail(btn) {
    document.getElementById("d_user").innerText = btn.dataset.user;
    document.getElementById("d_unit").innerText = btn.dataset.unit;
    document.getElementById("d_kegiatan").innerText = btn.dataset.kegiatan;
    document.getElementById("d_aset").innerText = btn.dataset.aset;
    document.getElementById("d_jumlah").innerText = btn.dataset.jumlah;
    document.getElementById("d_nohp").innerText = btn.dataset.nohp;
    document.getElementById("d_tanggal").innerText = btn.dataset.tanggal;
    document.getElementById("modalDetail").style.display = "block";
}

function tutupModal() {
    document.getElementById("modalDetail").style.display = "none";
}
</script>
</body>
</html>
