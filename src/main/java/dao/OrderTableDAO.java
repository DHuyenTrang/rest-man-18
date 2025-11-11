package dao;

import model.OrderTable;
import model.Table;

import java.sql.*;
import java.util.List;

public class OrderTableDAO extends DAO{
    public boolean saveOrder(OrderTable orderTable, List<Table> selectedTables) {
        String sqlOrder = "INSERT INTO tblOrderTable (note, startTime, endTime, tblCustomerId) VALUES (?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO tblOrderTableDetail (tblTableId, tblOrderTableId) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement psOrder = null;
        PreparedStatement psDetail = null;
        ResultSet rsKeys = null;

        int generatedOrderId = -1;
        try {
            conn = getConnection(); // Lấy kết nối từ lớp DAO cha
            if (conn == null) {
                System.err.println("Can't connect to database.");
                return false;
            }

            conn.setAutoCommit(false);

            psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, orderTable.getNote());

            psOrder.setTimestamp(2, Timestamp.valueOf(orderTable.getStartTime()));
            psOrder.setTimestamp(3, Timestamp.valueOf(orderTable.getEndTime()));
            psOrder.setInt(4, orderTable.getCustomer().getId());

            int rowsAffected = psOrder.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Tạo OrderTable thất bại, không có hàng nào được thêm.");
            }

            // Bước 2: Lấy ID tự động tăng vừa được tạo
            rsKeys = psOrder.getGeneratedKeys();
            if (rsKeys.next()) {
                generatedOrderId = rsKeys.getInt(1);
            } else {
                throw new SQLException("Tạo OrderTable thất bại, không lấy được ID.");
            }

            // Bước 3: Thêm vào tblOrderTableDetail (sử dụng batch)
            psDetail = conn.prepareStatement(sqlDetail);

            for (Table table : selectedTables) {
                psDetail.setInt(1, table.getId());
                psDetail.setInt(2, generatedOrderId);
                psDetail.addBatch();
            }
            psDetail.executeBatch();

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            generatedOrderId = -1;

        } finally {
            try {
                if (rsKeys != null) rsKeys.close();
                if (psOrder != null) psOrder.close();
                if (psDetail != null) psDetail.close();

                // Trả lại trạng thái auto-commit cho kết nối
                if (conn != null) {
                    conn.setAutoCommit(true);
                }

                closeConnection(conn); // Đóng kết nối
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return generatedOrderId != -1; // Trả về ID của đơn hàng mới
    }
}
