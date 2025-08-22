<%-- 
    Document   : createListing
    Created on : Aug 22, 2025, 1:41:34 PM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.axora.communityskillexchange.model.User" %>
<%
    User user = (User)session.getAttribute("user");
    if(user==null){
        response.sendRedirect("login.jsp?message=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Create Listing</h2>
    <form method="post" action="MainServlet">
        <input type="hidden" name="action" value="createListing">
        <div class="mb-3">
            <label>Type</label>
            <select name="type" class="form-control" required>
                <option value="">Select</option>
                <option value="Teaching">Teaching</option>
                <option value="Cooking">Cooking</option>
                <option value="Repair">Repair</option>
                <option value="Other">Other</option>
            </select>
        </div>
        <div class="mb-3">
            <label>Title</label>
            <input type="text" name="title" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control"></textarea>
        </div>
        <div class="mb-3">
            <label>City</label>
            <input type="text" name="city" class="form-control">
        </div>
        <div class="mb-3">
            <label>Price</label>
            <input type="text" name="price" class="form-control">
        </div>
        <button class="btn btn-primary">Create Listing</button>
    </form>
    <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>