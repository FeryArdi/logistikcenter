<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>LogistikCenter</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/landing.css">
</head>
<body>

<div class="landing-wrapper">

    <div class="landing-card">

        <h1>Selamat datang di LogistikCenter</h1>

        <p class="subtitle">
            Sistem peminjaman ruangan dan barang kampus.
        </p>

        <a href="<%=request.getContextPath()%>/views/login.jsp" class="btn-primary">
            Masuk ke Sistem
        </a>

    </div>

</div>

</body>
</html>
