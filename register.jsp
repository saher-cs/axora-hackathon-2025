<%-- 
    Document   : signup
    Created on : Aug 22, 2025, 11:51:49 AM
    Author     : ADMIN
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Community Skill Exchange</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Register</h2>
    <form method="post" action="MainServlet">
        <input type="hidden" name="action" value="register">
        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required minlength="6">
        </div>
        <div class="mb-3">
            <label>Phone</label>
            <input type="text" name="phone" class="form-control">
        </div>
        <div class="mb-3">
            <label>City</label>
            <input type="text" name="city" class="form-control">
        </div>
        <div class="mb-3">
            <label>Bio</label>
            <textarea name="bio" class="form-control"></textarea>
        </div>
        <button class="btn btn-success">Register</button>
    </form>
    <p class="mt-3">Already have an account? <a href="login.jsp">Login here</a></p>
    <p style="color:red;">
        <% if(request.getParameter("message")!=null){ out.print(request.getParameter("message")); } %>
    </p>
</div>
</body>
</html>