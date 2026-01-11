<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.gudang.model.User"%>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    String uri = request.getRequestURI();
%>

<div class="sidebar">

    <!-- HEADER -->
    <div class="sidebar-header">
        <div class="avatar">👤</div>
        <div class="title">Admin Sistem</div>
    </div>

    <a class="<%= uri.contains("admin-dashboard") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/admin-dashboard">
        <span class="icon">🏠</span>
        <span>Dashboard</span>
    </a>

    <a class="<%= uri.contains("admin-aset") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/admin-aset">
        <span class="icon">📦</span>
        <span>Manajemen Barang</span>
    </a>

    <a class="<%= uri.contains("admin-peminjaman") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/admin-peminjaman">
        <span class="icon">⏳</span>
        <span>Menunggu Persetujuan</span>
    </a>

    <a class="<%= uri.contains("admin-verifikasi") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/admin-verifikasi">
        <span class="icon">🔄</span>
        <span>Verifikasi Pengembalian</span>
    </a>

    <a class="<%= uri.contains("admin-riwayat") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/admin-riwayat">
        <span class="icon">📑</span>
        <span>Riwayat Peminjaman</span>
    </a>

    <a class="<%= uri.contains("kalender-admin") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/kalender-admin">
        <span class="icon">📅</span>
        <span>Kalender</span>
    </a>

    <hr>

    <a class="logout" href="<%=request.getContextPath()%>/login">
        <span class="icon">🚪</span>
        <span>Logout</span>
    </a>

</div>
