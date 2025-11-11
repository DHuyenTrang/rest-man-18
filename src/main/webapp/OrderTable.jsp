<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt bàn Online</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>

        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 15px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
            border: 1px solid #ccc;
            transition: background-color 0.3s, border-color 0.3s;
        }

        /* Nút "Tìm" (Giống mockup) */
        .btn-primary {
            color: #212529;
            background-color: #e0f7fa; /* Màu xanh nhạt (cyan) */
            border-color: #4dd0e1;
        }

        .btn-primary:hover {
            background-color: #b2ebf2;
            border-color: #0097a7;
        }

        /* Nút "Đặt bàn" (Giống mockup) */
        .btn-secondary {
            color: #212529;
            background-color: #e0f7fa; /* Màu xanh nhạt (cyan) */
            border-color: #4dd0e1;
            padding: 12px 25px; /* To hơn một chút */
            font-size: 16px;
        }

        .btn-secondary:hover {
            background-color: #b2ebf2;
            border-color: #0097a7;
        }

        /* Khu vực form tìm kiếm thời gian */
        .time-search-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
            margin-bottom: 30px;
        }

        .time-search-form h3 {
            margin-top: 0;
            font-size: 18px;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }

        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }

        .form-group label {
            min-width: 60px; /* Căn chỉnh "Ngày", "Giờ" */
            font-weight: bold;
            color: #555;
            margin-right: 15px;
        }

        .form-group input[type="date"],
        .form-group input[type="time"] {
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 15px;
        }

        .form-group span {
            margin: 0 10px;
        }

        /* Khu vực bảng danh sách bàn */
        .table-container {
            margin-top: 20px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
        }

        .table-container h3 {
            margin-top: 0;
            font-size: 18px;
        }

        .booking-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            border: 1px solid #ddd; /* Viền ngoài cho bảng */
        }

        .booking-table th,
        .booking-table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        .booking-table th {
            background-color: #f8f8f8;
            font-weight: bold;
        }

        .booking-table td:nth-child(1), /* STT */
        .booking-table td:nth-child(3), /* SL Khách */
        .booking-table td:nth-child(4) /* Chọn bàn */
        {
            text-align: center;
        }

        .booking-table input[type="checkbox"] {
            width: 18px;
            height: 18px;
        }

        /* Container cho nút Đặt Bàn cuối cùng */
        .submit-container {
            margin-top: 30px;
            text-align: center; /* Căn giữa nút "Đặt bàn" */
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>RestMan</h2>

    <div class="user-info-box">
        <p class="welcome-text">Xin chào,</p>
        <h3 class="user-name">${sessionScope.user.name}</h3>
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
        <h1>Đặt bàn online</h1>
    </div>

    <form action="order-table" method="POST">

        <div class="time-search-form">
            <h3>Thời gian</h3>

            <div class="form-group">
                <label for="inDatePicker">Ngày</label>
                <input type="date" id="inDatePicker" name="inDatePicker"
                       value="${requestScope.inDatePicker}" required>
            </div>

            <div class="form-group">
                <label for="inTimeStartPicker">Giờ</label>
                <input type="time" id="inTimeStartPicker" name="inTimeStartPicker"
                       value="${requestScope.inTimeStartPicker}" required>
                <span>—</span>
                <input type="time" id="inTimeEndPicker" name="inTimeEndPicker"
                       value="${requestScope.inTimeEndPicker}" required>
            </div>

            <button type="submit" name="action" value="find" class="btn btn-primary">
                Tìm
            </button>
        </div>


        <div class="table-container">
            <h3>Danh sách bàn trống</h3>

            <table class="booking-table" id="outListFreeTables">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên bàn</th>
                    <th>Số lượng khách</th>
                    <th>Chọn bàn</th>
                </tr>
                </thead>
                <tbody>

                <c:if test="${not empty outListFreeTables}">
                    <c:forEach var="table" items="${outListFreeTables}" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td>${table.name}</td>
                            <td>${table.capacity}</td>
                            <td>
                                <input type="checkbox" name="selectedTableIds" value="${table.id}">
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

                <c:if test="${empty outListFreeTables}">
                    <tr>
                        <td colspan="4" style="text-align: center; color: #777;">
                            Vui lòng chọn thời gian và nhấn "Tìm" để xem bàn trống.
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>

        <div class="submit-container">
            <button type="submit" name="action" value="book" class="btn btn-secondary">
                Đặt bàn
            </button>
        </div>

    </form>

</div>

</body>
</html>