package Service;


import DataBase.DAO.Directions;
import DataBase.DAO.Orders;
import DataBase.Entities.Direction;
import DataBase.Entities.Order;
import DataBase.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersService extends AbstractService {
    public OrdersService(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void processNewOrder() throws ServletException, IOException {
        auth();
        int orderId = Integer.parseInt(req.getParameter("id"));
        List<Direction> directions =  Directions.getDirections();
        for (Direction direct : directions) {
            if (direct.getId() == orderId) {
                session.setAttribute("newOrder", direct);
                req.getRequestDispatcher("jsp/orderProcess.jsp").forward(req, resp);
                break;
            }
        }
    }

    public void processChangeOrder() throws ServletException, IOException {
        auth();
        String id = req.getParameter("id");
        String removeId = req.getParameter("removeId");

        if (removeId != null) {
            try {
                Orders.removeOrder(Integer.parseInt(removeId));
            } catch (NumberFormatException e) {
                log.warn("NumberFormatException in changing...");
                req.getRequestDispatcher("jsp/showOrders.jsp");
            }
            req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);

        } else if (id != null) {
            session.setAttribute("changing", id);
            req.getRequestDispatcher("jsp/orderChange.jsp").forward(req, resp);
        }
    }

    public void processChangeComplete() throws ServletException, IOException {
        auth();
        int passCount = 0;
        try {
            passCount = Integer.parseInt(req.getParameter("passengersCount"));
        }catch (NumberFormatException e){
            log.warn("bad pass count!");
            req.getRequestDispatcher("jsp/orderChange.jsp").forward(req, resp);
        }
        String baggage = req.getParameter("baggage");
        String priotityQueue = req.getParameter("priotityQueue");

        boolean needBaggage = false;
        boolean needPriority = false;

        if (baggage != null){
            needBaggage = baggage.equals("on");
        }
        if (priotityQueue != null){
            needPriority = priotityQueue.equals("on");
        }

        int changingOrderId = Integer.parseInt((String) session.getAttribute("changing"));

        Orders.changeOrder(changingOrderId, passCount, needBaggage, needPriority);
        req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
    }

    private boolean checkBox(String checkBox) {
        if (checkBox == null) {
            return false;
        } else return checkBox.equals("on");
    }

    public void processPayment() throws ServletException, IOException {
        auth();
        Direction orderDirection = (Direction) session.getAttribute("newOrder");
        User thisUser = (User) session.getAttribute("user");
        int count = 0;
        try {
            count = Integer.parseInt(req.getParameter("passengersCount"));
        } catch (NumberFormatException e) {
            req.setAttribute("capacityFail", true);
            req.getRequestDispatcher("jsp/orderProcess.jsp").forward(req, resp);
        }
        boolean needBaggage = checkBox(req.getParameter("baggage"));
        boolean neenPriority = checkBox(req.getParameter("priotityQueue"));

        double summa = 0;

        summa += orderDirection.getBasicPrice();
        if (needBaggage) {
            summa += 45;
        }
        if (neenPriority) {
            summa += 30;
        }

        summa = summa * count;
        session.setAttribute("summa", summa);
        DataBase.Entities.Order order = Orders.addOrder(orderDirection, thisUser, count, needBaggage, neenPriority, summa);
        if (order == null) {
            req.setAttribute("capacityFail", true);
            req.getRequestDispatcher("jsp/orderProcess.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/jsp/payment.jsp").forward(req, resp);
        }
        req.setAttribute("capacityFail", false);
        session.setAttribute("order", order);
    }

    public void processPaymentComplete() throws ServletException, IOException {
        auth();
        String paramId = req.getParameter("id");
        DataBase.Entities.Order order = (Order) session.getAttribute("order");
        if (paramId != null) {
            Orders.updateOrderPay(Integer.parseInt(paramId), true);
        } else {
            if (order != null) Orders.updateOrderPay(order.getId(), true);
        }

        req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
    }
}
