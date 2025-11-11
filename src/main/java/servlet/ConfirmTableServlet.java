package servlet;

import dao.OrderTableDAO;
import model.Customer;
import model.OrderTable;
import model.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/confirm-booking")
public class ConfirmTableServlet extends HttpServlet {
    private OrderTableDAO orderTableDAO;

    @Override
    public void init() throws ServletException {
        orderTableDAO = new OrderTableDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        try {
            OrderTable orderTable = (OrderTable) session.getAttribute("orderTable");
            List<Table> selectedTables = (List<Table>) session.getAttribute("selectedTable");
            String note = req.getParameter("inNote");
            orderTable.setNote(note);
            orderTable.setCustomer((Customer) session.getAttribute("user"));
            boolean outSuccessNotification = orderTableDAO.saveOrder(orderTable, selectedTables);

            session.removeAttribute("tempOrder");
            session.removeAttribute("tempSelectedTables");

            if (outSuccessNotification) {
                session.setAttribute("outSuccessNotification", true);
                req.getRequestDispatcher("ConfirmTable.jsp").forward(req, resp);
            } else {
                session.setAttribute("outSuccessNotification", "Không thể lưu đơn đặt bàn. Đã có lỗi xảy ra. Vui lòng thử lại.");
                req.getRequestDispatcher("ConfirmTable.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("ConfirmTable.jsp").forward(req, resp);
        }
    }
}
