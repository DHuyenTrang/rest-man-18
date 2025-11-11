<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
<head>
    <title>Quản lý món ăn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>RestMan</h2>

    <div class="user-info-box">
        <p class="welcome-text">Xin chào,</p>
        <h3 class="user-name">${sessionScope.loggedInUser}</h3>
    </div>
    <ul>
        <li class="active"><a href="manage-dishes">Quản lý món ăn</a></li>
        <li class="#"><a href="">Quản lý Combo</a> </li>
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

            <c:forEach var="outDishes" items="${requestScope.dishes}">
                <div class="product-card">

                    <h4>${dish.name}</h4>

                    <p>${dish.detail}</p>

                    <span class="price">
                        <fmt:setLocale value="vi_VN"/>
                        <fmt:formatNumber value="${dish.price}" type="currency" currencySymbol="VNĐ"/>
                    </span>

                    <a href="edit-dish?id=${dish.id}" class="edit-link">Edit info</a>
                </div>
            </c:forEach>
            <c:if test="${empty requestScope.dishes}">
                <p>Chưa có món ăn nào. Vui lòng <a href="AddDish.jsp">thêm món mới</a>.</p>
            </c:if>

        </div>
    </div>
</div>

</body>
</html>