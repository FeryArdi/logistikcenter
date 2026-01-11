<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login | LogistikCenter</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">

    <!-- Font & Icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
</head>
<body>

<div class="login-wrapper">

    <div class="login-card">

        <!-- HEADER -->
        <div class="login-header">
            <div class="logo">
                <i class="fas fa-boxes-stacked"></i>
            </div>
            <h1>LogistikCenter</h1>
            <p>Sistem Otomatisasi Peminjaman Aset Kampus</p>
        </div>

        <!-- FORM -->
        <div class="login-body">

            <form action="<%=request.getContextPath()%>/login" method="post">

                <!-- EMAIL -->
                <div class="input-group">
                    <i class="fa fa-envelope"></i>
                    <input type="email"
                           name="email"
                           placeholder="Masukkan akun"
                           required>
                </div>
<!--                <small class="help-text">
                    Contoh: nama@telkomuniversity.ac.id
                </small>-->

                <!-- PASSWORD -->
                <div class="input-group">
                    <i class="fa fa-lock"></i>
                    <input type="password"
                           name="password"
                           placeholder="Password"
                           required>
                </div>

                <!-- ERROR -->
                <% if (request.getAttribute("error") != null) { %>
                    <div class="error">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>

                <!-- BUTTON -->
                <button type="submit" class="btn-login">
                    Login <i class="fa fa-arrow-right"></i>
                </button>

            </form>
        </div>

        <!-- FOOTER -->
        <div class="login-footer">
            © 2025 LogistikCenter - Telkom University
        </div>

    </div>

</div>

</body>
</html>
