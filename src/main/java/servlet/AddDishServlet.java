package servlet;

import dao.DishDAO;
import model.Dish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-dish")
public class AddDishServlet extends HttpServlet {
    private DishDAO dishDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        dishDAO = new DishDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Dish dish = new Dish();
        String name = req.getParameter("inDishName");
        String price = req.getParameter("inDishPrice");
        String detail = req.getParameter("inDishDetail");
        dish.setName(name);
        dish.setPrice((Float.parseFloat(price)));
        dish.setDetail(detail);

        boolean result = dishDAO.saveDish(dish);
        if (result) {
            req.getSession().setAttribute("outSuccessNotification", true);
            req.getRequestDispatcher("AddDish.jsp").forward(req, resp);
        } else {
            req.setAttribute("outSuccessNotification", "Thêm món ăn thất bại, vui lòng thử lại!");
            req.getRequestDispatcher("AddDish.jsp").forward(req, resp);
        }
    }
}
