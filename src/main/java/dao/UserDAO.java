package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO{
    public User checkLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String query = "select * from tblUser where username=? and password=?";

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
                user = new User();
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
        return  user;
    }
}
