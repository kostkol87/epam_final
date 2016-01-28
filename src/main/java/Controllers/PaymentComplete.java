package Controllers;

import DAO.Entities.Order;
import DAO.Utils.Orders;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/payOk")
public class PaymentComplete extends HttpServlet {
    private static final Logger log = Logger.getLogger(PaymentComplete.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paymentComplete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paymentComplete(req, resp);
    }

    protected void paymentComplete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String paramId = req.getParameter("id");
        DAO.Entities.Order order = (Order) session.getAttribute("order");
        if (paramId != null) {
            Orders.updateOrderPay(Integer.parseInt(paramId), true);
        } else {
            if (order != null) Orders.updateOrderPay(order.getId(), true);
        }

        req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
    }
}
