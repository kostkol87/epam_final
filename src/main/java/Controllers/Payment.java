package Controllers;


import DAObjects.Direction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/payment")
public class Payment extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPayment(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPayment(req, resp);
    }

    private boolean checkBox(String checkBox){
        if (checkBox == null) {
            return false;
        }else if (checkBox.equals("on")){
            return true;
        }else {
            return false;
        }
    }

    protected void processPayment(HttpServletRequest req, HttpServletResponse resp){
        //checkbox returns null or on
        HttpSession session = req.getSession(true);
        Direction orerDirection = (Direction) session.getAttribute("newOrder");
        int count = Integer.parseInt(req.getParameter("passengersCount"));
        boolean needBaggage = checkBox(req.getParameter("baggage"));
        boolean neenPriority = checkBox(req.getParameter("priotityQueue"));

        double summa = 0;

        summa+=orerDirection.getBasicPrice();
        if (needBaggage){
            summa+=45;
        }
        if (neenPriority){
            summa+=30;
        }

        summa = summa*count;
        session.setAttribute("summa", summa);

        try {
            req.getRequestDispatcher("jsp/payment.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
