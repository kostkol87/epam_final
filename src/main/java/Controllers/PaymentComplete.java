package Controllers;

import DAObjects.EntitiesUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/payOk")
public class PaymentComplete extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paymentComplete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paymentComplete(req, resp);
    }

    protected void paymentComplete(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        String paramId = req.getParameter("id");
        DAObjects.Order order = (DAObjects.Order) session.getAttribute("order");
        System.out.println("paramets id >>>>>> " + paramId);
        if (paramId != null){
            System.out.println("all od, param was fount, it is" + paramId);
            EntitiesUtils.updateOrderPay(Integer.parseInt(paramId), true);
        }else {
            if (order != null) {
                EntitiesUtils.updateOrderPay(order.getId(), true);
            }
        }
        try {
            req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
