<%-- 
    Document   : expressInterest
    Created on : Aug 22, 2025, 1:46:00 PM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.apn.axora.communityskillexchange.model.User" %>
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
    <title>Express Interest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Express Interest</h2>
    <form method="post" action="MainServlet">
        <input type="hidden" name="action" value="expressInterest">
        <div class="mb-3">
            <label>Listing ID</label>
            <input type="number" name="listingId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Message</label>
            <textarea name="message" class="form-control" required></textarea>
        </div>
        <button class="btn btn-primary">Send Interest</button>
    </form>
    <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>