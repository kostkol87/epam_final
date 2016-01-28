package Controllers;

import DAO.Entities.Direction;
import DAO.Utils.Directions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/newOrder")
public class Order extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proceesOrdering(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proceesOrdering(req, resp);
    }

    protected void proceesOrdering(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
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
}
