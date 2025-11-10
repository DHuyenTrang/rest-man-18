package dao;

import model.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DishDAO extends DAO{
    public boolean saveDish(Dish dish){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO tblDish (name, detail, price) VALUES (?, ?, ?)";
        try
        {
            conn = getConnection();
            if (conn == null) {
                System.err.println("Can't connect to database.");
                return false;
            }

            ps = conn.prepareStatement(query);
            ps.setString(1, dish.getName());
            ps.setString(2, dish.getDetail());
            ps.setDouble(3, dish.getPrice());

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
