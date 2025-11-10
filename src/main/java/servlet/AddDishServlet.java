package servlet;

import dao.DishDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/add-dish")
public class AddDishServlet extends HttpServlet {
    private DishDAO dishDAO;

}
