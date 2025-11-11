package dao;

import model.Table;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableDAO extends DAO{
    public List<Table> getTablesByIds(String[] ids) {
        List<Table> tables = new ArrayList<>();

        if (ids == null || ids.length == 0) {
            return tables;
        }
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM tblTable WHERE id IN (");

        for (int i = 0; i < ids.length; i++) {
            queryBuilder.append("?");
            if (i < ids.length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");
        String query = queryBuilder.toString();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            if (conn == null) {
                System.err.println("Can't connect to database.");
                return tables;
            }

            ps = conn.prepareStatement(query);

            for (int i = 0; i < ids.length; i++) {
                ps.setInt(i + 1, Integer.parseInt(ids[i]));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Table table = new Table();
                table.setId(rs.getInt("id"));
                table.setName(rs.getString("name"));
                tables.add(table);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tables;

    }

     public List<Table> getFreeTables(LocalDateTime startTime, LocalDateTime endTime) {
         List<Table> freeTables = new ArrayList<>();

         String sql = "SELECT T.id, T.name, T.capacity " +
                 "FROM tblTable T " +
                 "WHERE NOT EXISTS ( " +
                 "  SELECT 1 " + // Dùng 1 thay vì *
                 "  FROM tblOrderTable O " +
                 "  JOIN tblOrderTableDetail d ON O.id = d.tblOrderTableId " +
                 "  WHERE d.tblTableId = T.id " +
                 "    AND O.startTime <= ? " +
                 "    AND O.endTime >= ? " +
                 ")";
         try (Connection conn = getConnection(); // Lấy connection từ lớp DAO cha
              PreparedStatement ps = conn.prepareStatement(sql)) {

             Timestamp endTimestamp = Timestamp.valueOf(endTime);
             Timestamp startTimestamp = Timestamp.valueOf(startTime);

             ps.setTimestamp(1, endTimestamp);
             ps.setTimestamp(2, startTimestamp);

             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                 Table table = new Table();
                 table.setId(rs.getInt("id"));
                 table.setName(rs.getString("name"));
                 table.setCapacity(rs.getInt("capacity"));

                 freeTables.add(table);
                 System.out.println("Table ID: " + table.getId());
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return freeTables;
     }
}
