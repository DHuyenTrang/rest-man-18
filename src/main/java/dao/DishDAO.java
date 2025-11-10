package dao;

import model.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DishDAO extends DAO{
    public List<Dish> getAllDishes() {
        List<Dish> dishes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM tblDish";
        try {
            conn = getConnection();
            if (conn == null) {
                System.err.println("Can't connect to database.");
                return dishes;
            }

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name")); // Cột tên
                dish.setDetail(rs.getString("detail")); // Cột mô tả
                dish.setPrice(rs.getFloat("price")); // Cột giá

                dishes.add(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dishes;
    }
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
