package servlet;

import dao.DishDAO;
import model.Dish;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage-dishes")
public class MenuManageDishServlet extends HttpServlet {
    private DishDAO dishDAO;

    public void init() throws ServletException {
        dishDAO = new DishDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dish> dishes = dishDAO.getAllDishes();
        request.setAttribute("dishes", dishes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuManageDish.jsp");
        dispatcher.forward(request, response);
    }
}
