package Controllers;


import DAO.Entities.Direction;
import DAO.Entities.User;
import DAO.Utils.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/payment")
public class Payment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPayment(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPayment(req, resp);
    }

    private boolean checkBox(String checkBox) {
        if (checkBox == null) {
            return false;
        } else return checkBox.equals("on");
    }

    protected void processPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        Direction orderDirection = (Direction) session.getAttribute("newOrder");
        User thisUser = (User) session.getAttribute("user");
        int count = 0;
        try {
            count = Integer.parseInt(req.getParameter("passengersCount"));
        } catch (NumberFormatException e) {
            req.setAttribute("capacityFail", true);
            try {
                req.getRequestDispatcher("jsp/orderProcess.jsp").forward(req, resp);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
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
        DAO.Entities.Order order = Orders.addOrder(orderDirection, thisUser, count, needBaggage, neenPriority, summa);
        if (order == null) {
            req.setAttribute("capacityFail", true);
            req.getRequestDispatcher("jsp/orderProcess.jsp").forward(req, resp);
        }
        req.setAttribute("capacityFail", false);
        session.setAttribute("order", order);
    }
}
