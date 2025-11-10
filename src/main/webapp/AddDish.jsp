<%--
  File: AddDish.jsp
  Description: Form để thêm một món ăn mới vào kho.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Thêm món ăn mới</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
</head>
<body>

<div class="sidebar">
    <h2>Xin chào quản lý!</h2>
    <div class="user-info-box">
        <p class="user-name">${sessionScope.loggedInUser}</p>
    </div>
    <ul>
        <li class="active"><a href="manage-dishes">Quản lý món ăn</a></li>
        <li class="#"><a href="">Quản lý Combo</a> </li>
    </ul>
</div>

<div class="main-content">
    <div class="header">
        <h1>Thêm món ăn mới</h1>
        <span class="breadcrumb">
            <a href="manage-dishes" style="text-decoration: none; color: #007bff;" name="subBack">
                &#8592; Quay lại Quản lí món ăn
            </a>
        </span>
    </div>

    <c:if test="${sessionScope.outSuccessNotification == true}">
        <div class="notification success" id="success-alert">
            Thêm món ăn mới thành công!
        </div>
    </c:if>

    <c:remove var="showSuccessNotification" scope="session" />

    <script type="text/javascript">
        var alertBox = document.getElementById("success-alert");
        if (alertBox) {
            setTimeout(function() {
                alertBox.style.display = 'none';
                window.location.href = "manage-dishes";
            }, 3000);
        }
    </script>

    <div class="form-container">
        <form action="add-dish" method="POST">
            <div class="form-layout">

                <div class="form-fields">
                    <div class="form-group">
                        <label for="inDishName">Tên món ăn:</label>
                        <input type="text" id="inDishName" name="inDishName" required>
                    </div>

                    <div class="form-group">
                        <label for="inDishPrice">Giá:</label>
                        <input type="number" id="inDishPrice" name="inDishPrice" step="0.01" required>
                    </div>

                    <div class="form-group">
                        <label for="inDishDetail">Mô tả chi tiết:</label>
                        <textarea id="inDishDetail" name="inDishDetail"></textarea>
                    </div>
                </div>

            </div>

            <div style="text-align: right;">
                <button type="submit" class="submit-btn" name="subAdd">Lưu món ăn</button>
            </div>

        </form>
    </div>
</div>

</body>
</html>