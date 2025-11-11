<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Xác nhận Đặt Bàn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <style>
        .table-tags {
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
            margin-top: 8px;
        }
        .table-tag {
            background-color: #e0e0e0;
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 14px;
            font-weight: bold;
        }
        .form-data-display {
            font-size: 16px;
            font-weight: bold;
            color: #333;
            padding-top: 8px; /* Căn chỉnh với input */
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>Xin chào quản lý!</h2>
    <div class="user-info-box">
        <p class="user-name">${sessionScope.loggedInUser}</p>
    </div>
    <ul>
        <li><a href="MenuCustomer.jsp">Trang chủ</a></li>
        <li class="active"><a href="OrderTable.jsp">Đặt bàn</a></li>
        <li><a href="#">Lịch sử đặt bàn</a></li>
        <li><a href="#">Thông tin cá nhân</a></li>
        <li><a href="#">Đăng xuất</a></li>
    </ul>
</div>

<div class="main-content">
    <div class="header">
        <h1>Phiếu đặt bàn</h1>
        <%-- Nút "Quay lại" tương tự như trang AddDish.jsp --%>
        <span class="breadcrumb">
            <a href="order-table" style="text-decoration: none; color: #007bff;" name="subBack">
                &#8592; Quay lại Chọn bàn
            </a>
        </span>
    </div>

    <c:if test="${sessionScope.outSuccessNotification == true}">
        <div class="notification success" id="success-alert">
            Đặt bàn thành công!
        </div>
    </c:if>

    <c:remove var="showSuccessNotification" scope="session" />

    <script type="text/javascript">
        var alertBox = document.getElementById("success-alert");
        if (alertBox) {
            setTimeout(function() {
                alertBox.style.display = 'none';
                window.location.href = "MenuCustomer.jsp";
            }, 3000);
        }
    </script>


    <div class="form-container">
        <form action="confirm-booking" method="POST">
            <div class="form-layout">
                <div class="form-fields">
                    <c:set var="inOrderTable" value="${sessionScope.orderTable}" />

                    <div class="form-group">
                        <label>Thời gian:</label>
                        <p class="form-data-display">
                            <fmt:parseDate value="${inOrderTable.getStartTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartTime" type="both" />
                            <fmt:parseDate value="${inOrderTable.getEndTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndTime" type="both" />
                            <fmt:formatDate value="${parsedStartTime}" pattern="dd/MM/yyyy HH:mm" />
                            <fmt:formatDate value="${parsedEndTime}" pattern="HH:mm" />
                        </p>
                    </div>

                    <div class="form-group">
                        <label>Danh sách bàn đặt:</label>
                        <div class="table-tags">
                            <%-- Lấy danh sách bàn từ Session --%>
                            <c:forEach var="table" items="${sessionScope.selectedTable}">
                                <span class="table-tag">${table.getName()}</span>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inCustomerName">Họ tên khách hàng:</label>
                        <input type="text" id="inCustomerName" name="inCustomerName"
                               value="${sessionScope.user.getName()}" required>
                    </div>

                    <div class="form-group">
                        <label for="inCustomerEmail">Email:</label>
                        <input type="email" id="inCustomerEmail" name="inCustomerEmail"
                               value="${sessionScope.user.getName()}" required>
                    </div>

                    <div class="form-group">
                        <label for="inNote">Ghi chú:</label>
                        <textarea id="inNote" name="inNote" placeholder="Yêu cầu đặc biệt..."></textarea>
                    </div>

                </div>

            </div>

            <%-- Nút Submit --%>
            <div style="text-align: right;">
                <button type="submit" class="submit-btn" name="subConfirm">Xác nhận Đặt bàn</button>
            </div>

        </form>
    </div>
</div>

</body>
</html>