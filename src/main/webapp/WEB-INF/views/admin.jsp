<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Dashboard - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <div class="d-flex align-items-center gap-2">
        <a class="navbar-brand fw-bold" href="<c:url value='/admin'/>">Order Management System</a>
        <span class="badge bg-light text-primary">Admin</span>
    </div>
    <div class="d-flex gap-2">
        <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
    </div>
</nav>

<div class="container py-5">

    <div class="bg-primary text-white rounded-3 p-4 mb-4">
        <p class="text-uppercase small fw-semibold opacity-75 mb-1">Admin Panel</p>
        <h4 class="fw-bold mb-1">Admin Dashboard</h4>
        <p class="mb-0 opacity-75 small">Manage orders, products and system operations</p>
    </div>

    <div class="row g-3">
        <div class="col-md-4">
            <a href="<c:url value='/orders/all'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-primary ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">All Orders</h6>
                        </div>
                        <p class="text-muted small mb-0">View and manage all customer orders</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4">
            <a href="<c:url value='/products/list'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-primary ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">Manage Products</h6>
                        </div>
                        <p class="text-muted small mb-0">Add, edit or remove products</p>
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
                        <p class="text-muted small mb-0">Your personal order history</p>
                    </div>
                </div>
            </a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
