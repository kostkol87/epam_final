package Controllers;


import DAObjects.EntitiesUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeOrder")
public class ChangeOrder extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesChanging(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesChanging(req, resp);
    }

    protected void procesChanging(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        String removeId = req.getParameter("removeId");
        try {
            if (removeId != null) {
                try {
                    EntitiesUtils.removeOrder(Integer.parseInt(removeId));
                } catch (NumberFormatException e) {
                    //needlog
                    req.getRequestDispatcher("jsp/showOrders.jsp");
                }
                req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
            } else if (id != null) {
                session.setAttribute("changing", id);
                req.getRequestDispatcher("jsp/orderChange.jsp").forward(req, resp);
            }
        }catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
