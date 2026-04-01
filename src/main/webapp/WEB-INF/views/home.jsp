<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <a class="navbar-brand fw-bold" href="<c:url value='/home'/>">Order Management System</a>
    <div class="d-flex gap-2 align-items-center">
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/products/list'/>">Products</a>
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/orders/my'/>">My Orders</a>
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/vendor/login'/>">Vendor Portal</a>
        <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
    </div>
</nav>

<div class="container py-5">

    <div class="bg-primary text-white rounded-3 p-4 mb-4">
        <p class="text-uppercase small fw-semibold opacity-75 mb-1">Dashboard</p>
        <h4 class="fw-bold mb-1">Welcome back, ${username}</h4>
        <p class="mb-0 opacity-75 small">Manage your orders and browse available products</p>
    </div>

    <div class="row g-3">
        <div class="col-md-4">
            <a href="<c:url value='/products/list'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-primary ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">Browse Products</h6>
                        </div>
                        <p class="text-muted small mb-0">View and purchase available products</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4">
            <a href="<c:url value='/orders/my'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-primary ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">My Orders</h6>
                        </div>
                        <p class="text-muted small mb-0">Track your complete order history</p>
                    </div>
                </div>
            </a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
