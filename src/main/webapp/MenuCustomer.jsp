<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ Khách hàng</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <style>
        /* Style cho lời chào "Hi, Customer1!" */
        .main-content .header .user-greeting {
            font-size: 16px;
            font-weight: 500;
            color: #333;
        }

        /* Khu vực chứa các nút chức năng của khách hàng */
        .customer-options {
            /* Khoảng cách với header */
            margin-top: 40px;
        }

        /* Style cho nút "Đặt bàn" theo mockup */
        .book-table-btn {
            display: inline-block;
            padding: 12px 25px;
            font-size: 16px; /* Kích cỡ chữ */
            font-weight: bold;
            color: #212529; /* Màu chữ đen */

            /* Màu nền xanh nhạt (cyan) như mockup */
            background-color: #e0f7fa;

            /* Viền đậm hơn một chút */
            border: 1px solid #4dd0e1;

            border-radius: 6px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .book-table-btn:hover {
            /* Đậm hơn một chút khi hover */
            background-color: #b2ebf2;
            border-color: #0097a7;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>RestMan</h2>

    <div class="user-info-box">
        <p class="welcome-text">Xin chào,</p>
        <h3 class="user-name">${sessionScope.loggedInUser}</h3>
    </div>

    <ul>
        <li class="active"><a href="MenuCustomer.jsp">Trang chủ</a></li>
        <li class="#"><a href="OrderTable.jsp">Đặt bàn</a></li>
        <li><a href="#">Lịch sử đặt bàn</a></li>
        <li><a href="#">Thông tin cá nhân</a></li>
        <li><a href="#">Đăng xuất</a></li>
    </ul>
</div>

<div class="main-content">

    <div class="header">
        <h1>Trang chủ khách hàng</h1>
    </div>

    <div class="customer-options">

        <a href="OrderTable.jsp" class="book-table-btn">
            Đặt bàn
        </a>

    </div>

</div>