<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4 sticky-top shadow-sm">
    <a class="navbar-brand fw-bold" href="#">Order Management System</a>
    <div class="d-flex gap-2 align-items-center">
        <c:choose>
            <c:when test="${sessionScope.loggedInUser.role == 'ADMIN'}">
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/admin'/>">Dashboard</a>
            </c:when>
            <c:when test="${sessionScope.loggedInVendor != null}">
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/vendor/dashboard'/>">Dashboard</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/home'/>">Home</a>
                <a class="btn btn-outline-light btn-sm" href="<c:url value='/orders/my'/>">My Orders</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope.loggedInVendor != null}">
                <a class="btn btn-danger btn-sm" href="<c:url value='/vendor/logout'/>">Logout</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-danger btn-sm" href="<c:url value='/logout'/>">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<div class="container py-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h5 class="fw-bold text-dark mb-1">Product Catalogue</h5>
            <p class="text-muted small mb-0">Browse and manage available products</p>
        </div>
        <%-- ADMIN or VENDOR: Add Product button --%>
        <c:if test="${sessionScope.loggedInUser.role == 'ADMIN' || sessionScope.loggedInVendor != null}">
            <a class="btn btn-primary btn-sm fw-semibold" href="<c:url value='/products/add'/>">Add Product</a>
        </c:if>
    </div>

    <c:if test="${param.error == 'true'}">
        <div class="alert alert-danger py-2 small">An error occurred. Please try again.</div>
    </c:if>

    <div class="card border shadow-sm rounded-3 overflow-hidden">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
                <tr>
                    <th class="text-uppercase small text-muted fw-semibold" style="width:60px;">ID</th>
                    <th class="text-uppercase small text-muted fw-semibold">Product Name</th>
                    <th class="text-uppercase small text-muted fw-semibold">Description</th>
                    <th class="text-uppercase small text-muted fw-semibold" style="width:130px;">Price</th>
                    <th class="text-uppercase small text-muted fw-semibold" style="width:180px;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td class="text-muted small">${p.id}</td>
                        <td class="fw-semibold">${p.name}</td>
                        <td class="text-muted small">${p.description}</td>
                        <td class="fw-semibold">Rs. ${p.price}</td>
                        <td>
                            <c:choose>
                                <%-- ADMIN or VENDOR: Edit + Delete only --%>
                                <c:when test="${sessionScope.loggedInUser.role == 'ADMIN' || sessionScope.loggedInVendor != null}">
                                    <a class="btn btn-outline-secondary btn-sm me-1"
                                       href="<c:url value='/products/edit/${p.id}'/>">Edit</a>
                                    <a class="btn btn-outline-danger btn-sm"
                                       href="<c:url value='/products/delete/${p.id}'/>">Delete</a>
                                </c:when>
                                <%-- USER: Buy only --%>
                                <c:otherwise>
                                    <a class="btn btn-primary btn-sm"
                                       href="<c:url value='/orders/buy/${p.id}'/>">Buy Now</a>
                                </c:otherwise>
                            </c:choose>
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
