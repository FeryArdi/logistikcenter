<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.gudang.model.User"%>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    String uri = request.getRequestURI();
%>

<div class="sidebar">

    <!-- PROFIL USER -->
    <div class="sidebar-header">
        <div class="avatar">👤</div>
        <div class="name"><%= user.getName() %></div>
        <div class="role">User</div>
    </div>

    <a class="<%= uri.endsWith("/aset") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/aset">
        <span class="icon">🏠</span>
        <span>Dashboard</span>
    </a>

    <a class="<%= uri.endsWith("/peminjaman") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/peminjaman">
        <span class="icon">📦</span>
        <span>Pengajuan Peminjaman</span>
    </a>

    <a class="<%= uri.endsWith("/pengembalian") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/pengembalian">
        <span class="icon">🔄</span>
        <span>Pengembalian</span>
    </a>

    <a class="<%= uri.endsWith("/kalender") ? "active" : "" %>"
       href="<%=request.getContextPath()%>/kalender">
        <span class="icon">📅</span>
        <span>Kalender</span>
    </a>

    <hr>

    <a class="logout" href="<%=request.getContextPath()%>/login">
        <span class="icon">🚪</span>
        <span>Logout</span>
    </a>

</div>
