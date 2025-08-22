<%-- 
    Document   : dashboard
    Created on : Aug 22, 2025, 11:52:16 AM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.apn.axora.communityskillexchange.model.User"%>
<%@ page import="com.apn.axora.communityskillexchange.model.Listing"%>
<%@ page import="com.apn.axora.communityskillexchange.model.Interest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?message=Please login first");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Community Skill Exchange</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Welcome, <%= user.getName() %>!</h2>
    <div class="mb-3">
        <a href="createListing.jsp" class="btn btn-success">Create New Listing</a>
        <a href="login.jsp" class="btn btn-secondary">Logout</a>
    </div>

    <h4>My Listings</h4>
    <c:if test="${not empty myListings}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Title</th>
                <th>Description</th>
                <th>City</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="listing" items="${myListings}">
                <tr>
                    <td>${listing.id}</td>
                    <td>${listing.type}</td>
                    <td>${listing.title}</td>
                    <td>${listing.description}</td>
                    <td>${listing.city}</td>
                    <td>${listing.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty myListings}">
        <p>No listings found. Create one now!</p>
    </c:if>

    <h4 class="mt-5">Interests Received</h4>
    <c:if test="${not empty myInterests}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Listing ID</th>
                <th>From User ID</th>
                <th>Message</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="interest" items="${myInterests}">
                <tr>
                    <td>${interest.id}</td>
                    <td>${interest.listingId}</td>
                    <td>${interest.fromUserId}</td>
                    <td>${interest.message}</td>
                    <td>${interest.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty myInterests}">
        <p>No interests received yet.</p>
    </c:if>

</div>
</body>
</html>