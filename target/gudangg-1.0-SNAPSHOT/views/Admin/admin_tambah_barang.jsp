<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../layout/sidebar_admin.jsp" %>

<div class="content">

<h2>Tambah Barang Baru</h2>

    <form action="<%=request.getContextPath()%>/admin-aset" method="post">
    <input type="hidden" name="action" value="simpan">
    <label>Nama Barang</label>
    <input type="text" name="nama" required>
    <label>Stok Awal</label>
    <input type="number" name="stok" min="0" required>
    <label>Lokasi</label>
    <input type="text" name="lokasi" required>
<br><br>

<button class="btn-approve">💾 Simpan</button>
<a href="<%=request.getContextPath()%>/admin-aset">
    <button type="button">⬅ Kembali</button>
</a>

</form>

</div>
