package Controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveOrder")
public class SaveOrder extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proessSaving(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proessSaving(req, resp);
    }


    protected void proessSaving(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(">>>>> in save!");
        HttpSession session = req.getSession(true);
        DAObjects.Order order = (DAObjects.Order) session.getAttribute("order");
        try {
            req.getRequestDispatcher("jsp/showOrders.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
