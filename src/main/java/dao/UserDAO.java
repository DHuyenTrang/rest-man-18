package dao;

import model.Customer;
import model.Manager;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO {
    public User checkLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String query = "SELECT \n" +
                "    u.*, \n" +
                "    e.role AS employeeRole, \n" +
                "    c.tblUserId AS customerId \n" + // <-- Sửa ở đây
                "FROM \n" +
                "    tblUser u \n" +
                "LEFT JOIN \n" +
                "    tblEmployee e ON u.id = e.tblUserId \n" +
                "LEFT JOIN \n" +
                "    tblCustomer c ON u.id = c.tblUserId \n" +
                "WHERE \n" +
                "    u.username = ? AND u.password = ?";

        try {
            conn = getConnection();
            if (conn == null) {
                System.err.println("Can't connect to database.");
                return null;
            }

            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                String employeeRole = rs.getString("employeeRole");
                int customerId = rs.getInt("customerId");

                if ("Manager".equals(employeeRole)) {
                    user = new Manager();
                } else if (customerId > 0) {
                    user = new Customer();
                } else {
                    user = new User();
                }

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
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

        return user;
    }
}