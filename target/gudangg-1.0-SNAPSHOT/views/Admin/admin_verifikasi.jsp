<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.Peminjaman"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Verifikasi Pengembalian</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<%@ include file="../layout/sidebar_admin.jsp" %>

<div class="content">

<h2>Verifikasi Pengembalian Aset</h2>
<p>Peminjaman yang sudah disetujui dan menunggu pengembalian.</p>

<table class="riwayat-table">
<tr>
    <th>No</th>
    <th>Unit / Ormawa</th>
    <th>Aset</th>
    <th>Jumlah</th>
    <th>Tanggal</th>
    <th>Aksi</th>
</tr>

<%
List<Peminjaman> list =
    (List<Peminjaman>) request.getAttribute("listKembali");

int no = 1;
boolean ada = false;

if (list != null) {
    for (Peminjaman p : list) {
        if (!"disetujui".equals(p.getStatus())) continue;
        ada = true;
%>
<tr>
    <td><%= no++ %></td>
    <td><%= p.getNama_unit() %></td>
    <td><%= p.getNama_aset() %></td>
    <td><%= p.getJumlah() %></td>
    <td><%= p.getTgl_mulai() %> s/d <%= p.getTgl_selesai() %></td>
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
            ℹ️
        </button>
        <button class="btn-approve"
            onclick="konfirmasiPengembalian(<%= p.getId() %>)">
            ✔ Verifikasi</button>
    </td>
</tr>
<%
    }
}
%>

<% if (!ada) { %>
<tr>
    <td colspan="6" align="center">Tidak ada pengembalian menunggu verifikasi</td>
</tr>
<% } %>

</table>

</div>
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
<script>
function konfirmasiPengembalian(id) {
    const yakin = confirm(
        "Apakah barang yang dikembalikan SUDAH SESUAI?\n\n" +
        "Klik OK jika sesuai (peminjaman selesai)\n" +
        "Klik Cancel jika tidak sesuai"
    );

    if (yakin) {
        // lanjut verifikasi → status selesai
        window.location.href =
            "<%=request.getContextPath()%>/admin-peminjaman?action=verifikasi_kembali&id=" + id;
    }
    // jika Cancel → tidak melakukan apa-apa
}
</script>

</body>
</html>
