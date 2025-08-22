<%-- 
    Document   : login.jsp
    Created on : Aug 22, 2025, 11:37:55 AM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Community Skill Exchange</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Login</h2>
    <form method="post" action="MainServlet">
        <input type="hidden" name="action" value="login">
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <button class="btn btn-primary">Login</button>
    </form>
    <p class="mt-3">Don't have an account? <a href="register.jsp">Register here</a></p>
    <p style="color:red;">
        <% if(request.getParameter("message")!=null){ out.print(request.getParameter("message")); } %>
    </p>
</div>
</body>
</html>