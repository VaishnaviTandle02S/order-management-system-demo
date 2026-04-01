<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vendor Register - Order Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="min-vh-100 d-flex align-items-center justify-content-center py-5">
    <div class="card shadow-sm border" style="width:100%; max-width:420px;">
        <div class="card-body p-4 p-md-5">

            <div class="border-start border-4 border-warning ps-3 mb-4">
                <h4 class="fw-bold text-dark mb-1">Vendor Registration</h4>
                <p class="text-muted small mb-0">Create your vendor account to get started</p>
            </div>

            <c:if test="${not empty error}">
                <div class="alert alert-danger py-2 small">${error}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert alert-success py-2 small">${success}</div>
            </c:if>

            <form action="<c:url value='/vendor/register'/>" method="post">
                <div class="mb-3">
                    <label class="form-label fw-semibold small" for="name">Full Name</label>
                    <input class="form-control" type="text" id="name" name="name"
                           placeholder="Enter your full name" required />
                </div>
                <div class="mb-3">
                    <label class="form-label fw-semibold small" for="email">Email Address</label>
                    <input class="form-control" type="email" id="email" name="email"
                           placeholder="Enter your email" required />
                </div>
                <div class="mb-4">
                    <label class="form-label fw-semibold small" for="password">Password</label>
                    <input class="form-control" type="password" id="password" name="password"
                           placeholder="Choose a password" required />
                </div>
                <button class="btn btn-warning w-100 fw-semibold text-dark" type="submit">Create Vendor Account</button>
            </form>

            <hr class="my-4">

            <p class="text-center text-muted small mb-0">
                Already registered? <a href="<c:url value='/vendor/login'/>" class="text-primary fw-semibold text-decoration-none">Sign In</a>
            </p>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
