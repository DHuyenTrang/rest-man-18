<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Quản lý món ăn</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>

<div class="sidebar">
    <h2>Xin chào quản lý!</h2>
    <h3>${sessionScope.loggedInUser}</h3>
    <ul>
        <li class="active"><a href="MenuManageDish.jsp">Quản lý món ăn</a></li>
    </ul>
</div>

<div class="main-content">
    <div class="header">
        <h1>Quản lí món ăn</h1>
        <a href="AddDish.jsp" class="add-new-btn" name="subAddDish">Thêm món mới</a>
    </div>

    <div class="section">
        <h3>Danh sách món ăn</h3>
        <div class="product-grid">
            <div class="product-card">
                <h4>Chocolate Brownie</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                <span class="price">$15.00</span>
                <a href="#" class="edit-link">Edit info</a>
            </div>
            <div class="product-card">
                <h4>Buger</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                <span class="price">$10.00</span>
                <a href="#" class="edit-link">Edit info</a>
            </div>
            <div class="product-card">
                <h4>Macarons</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                <span class="price">$12.00</span>
                <a href="#" class="edit-link">Edit info</a>
            </div>
        </div>
    </div>

</div>

</body>
</html>