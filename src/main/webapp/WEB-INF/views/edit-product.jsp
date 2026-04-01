<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Product - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <a class="navbar-brand fw-bold" href="#">Order Management System</a>
    <div class="d-flex gap-2">
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/products/list'/>">Products</a>
        <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
    </div>
</nav>

<div class="container py-4" style="max-width:560px;">

    <h5 class="fw-bold text-dark mb-1">Edit Product</h5>
    <p class="text-muted small mb-4">Update the product details below</p>

    <div class="card border shadow-sm rounded-3">
        <div class="card-body p-4">

            <form action="<c:url value='/products/update'/>" method="post">

                <input type="hidden" name="id" value="${product.id}" />

                <div class="mb-3">
                    <label class="form-label fw-semibold small"><input class="form-control" type="text" name="name" value="${product.name}" required />
                    Product Name</label>

                </div>
                <div class="mb-3">
                    <label class="form-label fw-semibold small"><input class="form-control" type="text" name="description" value="${product.description}" />
                    Description</label>

                </div>
                <div class="mb-4">
                    <label class="form-label fw-semibold small"><input class="form-control" type="number" name="price" value="${product.price}" step="0.01" required />
                    Price (Rs.)</label>

                </div>
                <hr class="my-3">
                <div class="d-flex justify-content-end gap-2">
                    <a class="btn btn-outline-secondary" href="<c:url value='/products/list'/>">Cancel</a>
                    <button class="btn btn-primary fw-semibold" type="submit">Save Changes</button>
                </div>

            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
