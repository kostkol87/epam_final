package Controllers;


import DAObjects.EntitiesUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeComplete")
public class ChangeComplete extends HttpServlet{
    private static final Logger log = Logger.getLogger(ChangeComplete.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processChanged(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processChanged(req, resp);
    }

    protected void processChanged(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        int passCount = Integer.parseInt(req.getParameter("passengersCount"));
        boolean needBaggage = Boolean.parseBoolean(req.getParameter("baggage"));
        boolean needPriority = Boolean.parseBoolean(req.getParameter("priotityQueue"));
        int changingOrderId = (int) session.getAttribute("changing");
        EntitiesUtils.changeOrder(changingOrderId, passCount, needBaggage, needPriority);
        try {
            req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
            log.warn("forward troubles!");
        } catch (ServletException | IOException e) {
            try {
                req.getRequestDispatcher("jsp/workspace.jsp.jsp").forward(req, resp);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
