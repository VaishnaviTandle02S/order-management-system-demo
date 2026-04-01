<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Product - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <div class="d-flex align-items-center gap-2">
        <a class="navbar-brand fw-bold" href="#">Order Management System</a>
        <c:choose>
            <c:when test="${sessionScope.loggedInVendor != null}">
                <span class="badge bg-warning text-dark">Vendor: ${sessionScope.loggedInVendor.name}</span>
            </c:when>
            <c:otherwise>
                <span class="badge bg-light text-primary">Admin: ${sessionScope.loggedInUser.username}</span>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="d-flex gap-2">
        <c:choose>
            <c:when test="${sessionScope.loggedInVendor != null}">
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/vendor/dashboard'/>">Dashboard</a>
                <a class="btn btn-danger btn-sm" href="<c:url value='/vendor/logout'/>">Logout</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/products/list'/>">Products</a>
                <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<div class="container py-4" style="max-width:560px;">

    <h5 class="fw-bold text-dark mb-1">Add New Product</h5>
    <p class="text-muted small mb-4">Fill in the details to list a new product</p>

    <div class="card border shadow-sm rounded-3">
        <div class="card-body p-4">

            <c:if test="${not empty error}">
                <div class="alert alert-danger py-2 small">${error}</div>
            </c:if>

            <form action="<c:url value='/products/add'/>" method="post">
                <div class="mb-3">
                    <label class="form-label fw-semibold small">
                    <input class="form-control" type="text" name="name" placeholder="Enter product name" required />Product Name</label>

                </div>
                <div class="mb-3">
                    <label class="form-label fw-semibold small"><input class="form-control" type="text" name="description" placeholder="Enter description" />
                    Description</label>

                </div>
                <div class="mb-4">
                    <label class="form-label fw-semibold small">
                    <input class="form-control" type="number" name="price" placeholder="0.00" step="0.01" required />
                    Price (Rs.)</label>
                </div>
                <hr class="my-3">
                <div class="d-flex justify-content-end gap-2">
                    <a class="btn btn-outline-secondary" href="<c:url value='/products/list'/>">Cancel</a>
                    <button class="btn btn-primary fw-semibold" type="submit">Add Product</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
