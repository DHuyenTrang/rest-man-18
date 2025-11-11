package servlet;

import dao.TableDAO;
import model.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    }

    private void findFreeTables(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dateStr = request.getParameter("inDatePicker");
            String timeStartStr = request.getParameter("inTimeStartPicker");
            String timeEndStr = request.getParameter("inTimeEndPicker");

            LocalDateTime startTime = LocalDateTime.parse(dateStr + "T" + timeStartStr);
            LocalDateTime endTime = LocalDateTime.parse(dateStr + "T" + timeEndStr);

            List<Table> freeTables = tableDAO.getTablesInTime(startTime, endTime);

            request.setAttribute("outListFreeTables", freeTables);
            request.setAttribute("inDatePicker", dateStr);
            request.setAttribute("inTimeStartPicker", timeStartStr);
            request.setAttribute("inTimeEndPicker", timeEndStr);

            request.getRequestDispatcher("OrderTable.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void bookTables(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try {
//            // 1. Lấy (các) ID bàn đã chọn
//            String[] selectedTableIds = request.getParameterValues("selectedTableIds");
//
//            String dateStr = request.getParameter("selectedDate");
//            String timeStartStr = request.getParameter("startTime");
//            String timeEndStr = request.getParameter("endTime");
//
//            HttpSession session = request.getSession();
//            Customer customer = (Customer) session.getAttribute("user");
//
//            // --- Validation (Kiểm tra) ---
//            if (customer == null) {
//                // Chưa đăng nhập
//                response.sendRedirect("Login.jsp"); // Chuyển về trang login
//                return;
//            }
//            if (selectedTableIds == null || selectedTableIds.length == 0) {
//                // Chưa chọn bàn nào
//                request.setAttribute("error", "Vui lòng chọn ít nhất một bàn.");
//                findFreeTables(request, response); // Tải lại trang với danh sách bàn
//                return;
//            }
//            // --- Hết Validation ---
//
//            // 4. Tạo đối tượng OrderTable
//            LocalDateTime startTime = LocalDateTime.parse(dateStr + "T" + timeStartStr);
//            LocalDateTime endTime = LocalDateTime.parse(dateStr + "T" + timeEndStr);
//
//            OrderTable newOrder = new OrderTable();
//            newOrder.setStartTime(startTime);
//            newOrder.setEndTime(endTime);
//            newOrder.setCustomerId(customer.getId()); // Giả sử model OrderTable có setCustomerId
//            newOrder.setNote("Đặt bàn online");
//
//            // 5. Gọi DAO để lưu vào CSDL (đây là bước quan trọng)
//            // Hàm này sẽ:
//            //   a. Thêm 1 dòng vào tblOrderTable
//            //   b. Lấy newOrderTableId vừa tạo
//            //   c. Thêm N dòng vào tblOrderTableDetail (dựa trên selectedTableIds)
//            //   d. Trả về ID của đơn vừa tạo
//            int newOrderId = orderTableDAO.createBooking(newOrder, selectedTableIds);
//
//            // 6. Chuyển hướng (Redirect) sang trang xác nhận
//            response.sendRedirect("confirmTable.jsp?orderId=" + newOrderId);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Xử lý lỗi
//        }
//    }
}
