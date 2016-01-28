package Controllers;

import DAO.Utils.Directions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDirection")
public class RemoveDirection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }

    /**
     * check, if where are no paid orders with current flight - direction: removes one,
     * else tell that some tickets has already bought...
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void processRemoving(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int rmDirId = Integer.valueOf(req.getParameter("id"));
        String redirection = "10; URL=http://" + req.getHeader("host") + "/jsp/workspace.jsp";

        if (Directions.isEmptyDirection(rmDirId)) {
            Directions.removeDirection(rmDirId);
            resp.getWriter().print("Direction was removed successfully!\n you'll be redirect in 10 sec.");
            resp.setHeader("Refresh", redirection);

        } else {
            resp.getWriter().print("There are passengers who have already bought tickets!" +
                    "May be we're low-cost, but we're too honest =)" +
                    "\n you'll be redirect in 10 sec.");
            resp.setHeader("Refresh", redirection);
        }
    }
}
