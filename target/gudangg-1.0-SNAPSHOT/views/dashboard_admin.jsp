<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.gudang.model.User"%>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }
%>

<%
    String namaDepan = user.getName().split(" ")[0];
%>

<h2>Selamat datang, <%= namaDepan %></h2>


<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<%@ include file="layout/sidebar.jsp" %>

<div class="content">
    <div class="box">
        <h2>Selamat datang, <%= namaDepan %></h2>
        <p>Silakan pilih menu di sidebar untuk melanjutkan.</p>
    </div>
</div>

</body>
</html>
