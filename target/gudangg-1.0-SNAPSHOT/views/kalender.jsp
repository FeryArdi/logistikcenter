<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.gudang.model.Peminjaman"%>
<%@page import="java.time.*"%>

<!DOCTYPE html>
<html>
<head>
    <title>Kalender Peminjaman Ruangan</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<%@ include file="layout/sidebar_admin.jsp" %>

<div class="content">

<h2>Kalender Peminjaman Ruangan</h2>

<!-- ================= FILTER BULAN & TAHUN ================= -->
<form method="get" action="<%=request.getContextPath()%>/kalender">
    Bulan:
    <select name="bulan">
        <% 
        int bulan = (Integer) request.getAttribute("bulan");
        int tahun = (Integer) request.getAttribute("tahun");
        for (int i = 1; i <= 12; i++) { 
        %>
            <option value="<%=i%>" <%= (i == bulan) ? "selected" : "" %>>
                <%= Month.of(i) %>
            </option>
        <% } %>
    </select>

    Tahun:
    <input type="number" name="tahun" value="<%=tahun%>" />

    <button type="submit">Tampilkan</button>
</form>

<hr>

<!-- ================= KALENDER ================= -->
<%
List<String> tanggalDipinjam =
    (List<String>) request.getAttribute("tanggalDipinjam");

YearMonth ym = YearMonth.of(tahun, bulan);
int totalHari = ym.lengthOfMonth();
int startDay = LocalDate.of(tahun, bulan, 1)
                        .getDayOfWeek().getValue() % 7;
%>
<div class="calendar-wrapper">
<div class="calendar-header">
    <div>Minggu</div>
    <div>Senin</div>
    <div>Selasa</div>
    <div>Rabu</div>
    <div>Kamis</div>
    <div>Jumat</div>
    <div>Sabtu</div>
</div>

<div class="calendar-grid">
<% for (int i = 0; i < startDay; i++) { %>
    <div></div>
<% } %>

<% for (int d = 1; d <= totalHari; d++) {
    String tgl = String.format("%04d-%02d-%02d", tahun, bulan, d);
    boolean ada = tanggalDipinjam.contains(tgl);
%>
<div class="day <%= ada ? "has-event" : "" %>"
     onclick="pilihTanggal('<%= tgl %>')">
    <%= d %>
</div>
<% } %>
</div>
</div>

<hr>

<!-- ================= TABEL DETAIL ================= -->
<h3>Daftar Ruangan Dipinjam</h3>

<table class="riwayat-table">
<tr>
    <th>No</th>
    <th>Unit / Ormawa</th>
    <th>Nama Kegiatan</th>
    <th>Ruangan</th>
    <th>Tanggal Mulai</th>
    <th>Tanggal Selesai</th>
</tr>

<%
List<Peminjaman> list =
    (List<Peminjaman>) request.getAttribute("list");

String tanggalDipilih =
    (String) request.getAttribute("tanggalDipilih");

int no = 1;

if (list != null && !list.isEmpty()) {
    for (Peminjaman p : list) {
%>
<tr>
    <td><%= no++ %></td>
    <td><%= p.getNama_unit() %></td>
    <td><%= p.getNama_kegiatan() %></td>
    <td><%= p.getNama_aset() %></td>
    <td><%= p.getTgl_mulai() %></td>
    <td><%= p.getTgl_selesai() %></td>
</tr>
<% } 
} else { %>
<tr>
    <td colspan="6" style="text-align:center">
        <%= (tanggalDipilih != null)
            ? "Tidak ada peminjaman ruangan pada tanggal " + tanggalDipilih
            : "Tidak ada peminjaman ruangan pada bulan ini" %>
    </td>
</tr>
<% } %>
</table>

</div>

<script>
function pilihTanggal(tgl) {
    const url =
        "<%=request.getContextPath()%>/kalender-admin"
        + "?bulan=<%=bulan%>&tahun=<%=tahun%>&tanggal=" + tgl;
    window.location.href = url;
}
</script>

</body>
</html>
