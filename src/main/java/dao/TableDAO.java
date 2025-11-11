package dao;

import model.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableDAO extends DAO{
     public List<Table> getTablesInTime(LocalDateTime startTime, LocalDateTime endTime) {
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
