<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.*"%>

<%
    // =====================
    // CEK LOGIN
    // =====================
    User userLogin = (User) session.getAttribute("user");
    if (userLogin == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }
    
        // =====================
    // DATA UNIT PEMINJAM
    // =====================
    UnitPeminjamDAO unitDAO = new UnitPeminjamDAO();
    List<UnitPeminjam> unitList = unitDAO.getAll();

    // =====================
    // DATA ASET
    // =====================
    AsetDAO asetDAO = new AsetDAO();
    List<Aset> asetList = asetDAO.getAll();

    // =====================
    // RIWAYAT PEMINJAMAN USER
    // =====================
    PeminjamanDAO pemDAO = new PeminjamanDAO();
    List<Peminjaman> riwayat = pemDAO.getByUser(userLogin.getId());
%>


<!DOCTYPE html>
<html>
<head>
    <title>Ajukan Peminjaman</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

<script>
function cekTipe() {
    const select = document.getElementById("aset");
    const tipe = select.options[select.selectedIndex].dataset.tipe;
    const box = document.getElementById("jumlahBox");
    const inputJumlah = box.querySelector('input[name="jumlah"]');

    if (tipe === "barang") {
        // Aktifkan
        box.classList.remove("disabled");
        inputJumlah.disabled = false;
    } else {
        // Nonaktifkan (untuk "ruangan" atau lainnya)
        box.classList.add("disabled");
        inputJumlah.disabled = true;
        // Opsional: set nilai default (misalnya 1, karena ruangan = 1 unit)
        inputJumlah.value = "1";
    }
}
</script>

</head>

<body>

<!-- SIDEBAR -->
<%@ include file="layout/sidebar.jsp" %>

<!-- KONTEN -->
<div class="content">

    <!-- ================= FORM ================= -->
    <div class="form-card">

        <h2>Pengajuan Peminjaman</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/peminjaman" method="post">

<div class="form-grid">

<!--    <div>
        <label>Nama Unit / UKM / Ormawa</label>
        <input type="text" name="nama_unit" required>
    </div>-->

    <!-- UNIT -->
    <div>
        <label>Unit / Ormawa</label>
        <select name="unit_id" required>
            <option value="">-- Pilih Unit --</option>
            <% for (UnitPeminjam u : unitList) { %>
                <option value="<%=u.getId()%>">
                    <%=u.getNama_unit()%> (<%=u.getJenis()%>)
                </option>
            <% } %>
        </select>
    </div>

    <div>
        <label>Nama Kegiatan</label>
        <input type="text" name="nama_kegiatan" required>
    </div>

    <div>
        <label>No HP Penanggung Jawab</label>
        <input type="text" name="no_hp" required>
    </div>

    <div>
        <label>Aset</label>
        <select id="aset" name="aset_id" onchange="cekTipe()" required>
            <% for(Aset a : asetList){ %>
                <option value="<%=a.getId()%>" data-tipe="<%=a.getTipe()%>">
                    <%=a.getNama()%> — (<%=a.getTipe()%>)
                </option>
            <% } %>
        </select>
    </div>

<div id="jumlahBox">
    <label>Jumlah Barang</label>
    <input type="number" name="jumlah" min="1" value="1">
</div>

    <div></div>

    <div>
        <label>Tanggal Mulai</label>
        <input type="date" name="tgl_mulai" required>
    </div>

    <div>
        <label>Tanggal Selesai</label>
        <input type="date" name="tgl_selesai" required>
    </div>

    <div></div>

</div>

<button type="submit">Ajukan Peminjaman</button>
</form>

    </div>

    <!-- ================= RIWAYAT ================= -->
    <div class="riwayat-card">

<h2>Status Peminjaman Saya</h2>

<table class="riwayat-table">
<tr>
    <th>No</th>
    <th>Unit / Ormawa</th>
    <th>Aset Peminjaman</th>
    <th>Jumlah</th>
    <th>Tanggal</th>
    <th>Status</th>
</tr>

<%
int no = 1;
if (riwayat != null && !riwayat.isEmpty()) {
    for (Peminjaman p : riwayat) {
        Aset a = asetDAO.getById(p.getAset_id());
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
</tr>
<% }} else { %>
<tr>
    <td colspan="6" style="text-align:center;">Belum ada peminjaman</td>
</tr>
<% } %>
</table>

</div>

</div>

<script>
    cekTipe();
</script>

</body>
</html>
