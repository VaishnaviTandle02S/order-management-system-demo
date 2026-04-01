<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Orders - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <a class="navbar-brand fw-bold" href="<c:url value='/home'/>">Order Management System</a>
    <div class="d-flex gap-2">
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/home'/>">Home</a>
        <a class="btn btn-outline-light btn-sm" href="<c:url value='/products/list'/>">Products</a>
        <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
    </div>
</nav>

<div class="container py-4">

    <h5 class="fw-bold text-dark mb-1">My Orders</h5>
    <p class="text-muted small mb-4">Your personal order history</p>

    <div class="card border shadow-sm rounded-3 overflow-hidden">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
                <tr>
                    <th class="text-uppercase small text-muted fw-semibold">Order ID</th>
                    <th class="text-uppercase small text-muted fw-semibold">Product</th>
                    <th class="text-uppercase small text-muted fw-semibold">Total Price</th>
                    <th class="text-uppercase small text-muted fw-semibold">Quantity</th>
                    <th class="text-uppercase small text-muted fw-semibold">Order Date</th>
                    <th class="text-uppercase small text-muted fw-semibold">Summary</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td class="fw-semibold">#${o.id}</td>
                        <td class="fw-semibold">${o.product.name}</td>
                        <td class="fw-semibold">Rs. ${o.totalPrice}</td>
                        <td>${o.quantity}</td>
                        <td class="text-muted small">${o.orderDate}</td>
                        <td>
                            <a class="btn btn-outline-secondary btn-sm"
                               href="<c:url value='/orders/summary/${o.id}'/>">View Summary</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
