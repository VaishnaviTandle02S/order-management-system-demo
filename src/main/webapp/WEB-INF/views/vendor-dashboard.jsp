<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vendor Dashboard - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-warning px-4 sticky-top shadow-sm">
    <div class="d-flex align-items-center gap-2">
        <a class="navbar-brand fw-bold text-dark" href="<c:url value='/vendor/dashboard'/>">Order Management System</a>
        <span class="badge bg-dark text-warning">Vendor</span>
    </div>
    <div class="d-flex gap-2">
        <a class="btn btn-dark btn-sm" href="<c:url value='/vendor/logout'/>">Logout</a>
    </div>
</nav>

<div class="container py-5">

    <div class="bg-warning rounded-3 p-4 mb-4">
        <p class="text-uppercase small fw-semibold text-dark opacity-75 mb-1">Vendor Panel</p>
        <h4 class="fw-bold text-dark mb-1">Welcome, ${vendorName}</h4>
        <p class="mb-0 text-dark opacity-75 small">Manage your products and review incoming orders</p>
    </div>

    <div class="row g-3">
        <div class="col-md-4">
            <a href="<c:url value='/products/list'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-warning ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">View Products</h6>
                        </div>
                        <p class="text-muted small mb-0">Browse and manage your product catalogue</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4">
            <a href="<c:url value='/orders/all'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-warning ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">View Orders</h6>
                        </div>
                        <p class="text-muted small mb-0">Review all customer orders</p>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4">
            <a href="<c:url value='/products/add'/>" class="text-decoration-none">
                <div class="card h-100 border shadow-sm">
                    <div class="card-body p-4">
                        <div class="border-start border-4 border-warning ps-3 mb-3">
                            <h6 class="fw-bold text-dark mb-0">Add Product</h6>
                        </div>
                        <p class="text-muted small mb-0">List a new product for sale</p>
                    </div>
                </div>
            </a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
