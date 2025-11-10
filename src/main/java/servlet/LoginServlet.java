package servlet;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String pass = request.getParameter("password");

        User user = userDAO.checkLogin(username, pass);
        if (user == null) {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        else {
            request.getSession().setAttribute("user", user);
            request.getSession(true).setAttribute("loggedInUser", user.getName());
            response.sendRedirect("manage-dishes");
        }
    }
}
