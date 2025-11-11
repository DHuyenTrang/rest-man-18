package servlet;

import dao.TableDAO;
import model.OrderTable;
import model.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/order-table")
public class OrderTableServlet extends HttpServlet {
    private TableDAO tableDAO;

    @Override
    public void init(){
        tableDAO =  new TableDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("find".equals(action)){
            findFreeTables(req,resp);
        }
        if ("book".equals(action)){
            bookTables(req,resp);
        }
    }

    private void findFreeTables(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dateStr = request.getParameter("inDatePicker");
            String timeStartStr = request.getParameter("inTimeStartPicker");
            String timeEndStr = request.getParameter("inTimeEndPicker");

            LocalDateTime startTime = LocalDateTime.parse(dateStr + "T" + timeStartStr);
            LocalDateTime endTime = LocalDateTime.parse(dateStr + "T" + timeEndStr);

            List<Table> freeTables = tableDAO.getFreeTables(startTime, endTime);

            request.setAttribute("outListFreeTables", freeTables);
            request.setAttribute("inDatePicker", dateStr);
            request.setAttribute("inTimeStartPicker", timeStartStr);
            request.setAttribute("inTimeEndPicker", timeEndStr);

            request.getRequestDispatcher("OrderTable.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bookTables(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String[] selectedTableIds = request.getParameterValues("outSelectedTableIds");

            String dateStr = request.getParameter("inDatePicker");
            String timeStartStr = request.getParameter("inTimeStartPicker");
            String timeEndStr = request.getParameter("inTimeEndPicker");

            if (selectedTableIds == null || selectedTableIds.length == 0) {
                request.setAttribute("error", "Vui lòng chọn ít nhất một bàn.");
                findFreeTables(request, response);
                return;
            }

            // 4. Tạo đối tượng OrderTable
            LocalDateTime startTime = LocalDateTime.parse(dateStr + "T" + timeStartStr);
            LocalDateTime endTime = LocalDateTime.parse(dateStr + "T" + timeEndStr);

            OrderTable newOrder = new OrderTable();
            newOrder.setStartTime(startTime);
            newOrder.setEndTime(endTime);

            HttpSession session = request.getSession();
            session.setAttribute("orderTable", newOrder);
            session.setAttribute("selectedTable", tableDAO.getTablesByIds(selectedTableIds));

            response.sendRedirect("ConfirmTable.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi
        }
    }
}
