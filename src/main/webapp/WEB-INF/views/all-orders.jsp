<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Orders - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <a class="navbar-brand fw-bold" href="#">Order Management System</a>
    <div class="d-flex gap-2">
        <c:choose>
            <c:when test="${sessionScope.loggedInVendor != null}">
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/vendor/dashboard'/>">Dashboard</a>
                <a class="btn btn-danger btn-sm" href="<c:url value='/vendor/logout'/>">Logout</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/admin'/>">Dashboard</a>
                <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<div class="container py-4">

    <h5 class="fw-bold text-dark mb-1">All Orders</h5>
    <p class="text-muted small mb-4">Complete order history across all customers</p>

    <div class="card border shadow-sm rounded-3 overflow-hidden">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
                <tr>
                    <th class="text-uppercase small text-muted fw-semibold">Order ID</th>
                    <th class="text-uppercase small text-muted fw-semibold">Customer</th>
                    <th class="text-uppercase small text-muted fw-semibold">Product</th>
                    <th class="text-uppercase small text-muted fw-semibold">Total Price</th>
                    <th class="text-uppercase small text-muted fw-semibold">Qty</th>
                    <th class="text-uppercase small text-muted fw-semibold">Order Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td class="fw-semibold">#${order.id}</td>
                        <td><c:out value="${order.user.username}" default="N/A"/></td>
                        <td class="fw-semibold"><c:out value="${order.product.name}" default="N/A"/></td>
                        <td class="fw-semibold">Rs. ${order.totalPrice}</td>
                        <td>${order.quantity}</td>
                        <td class="text-muted small"><c:out value="${order.orderDate}" default="N/A"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
