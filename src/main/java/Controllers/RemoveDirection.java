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
    protected void processRemoving(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int rmDirId = Integer.valueOf(req.getParameter("id"));
        String redirection = "5; URL=http://" + req.getHeader("host") + "/jsp/workspace.jsp";

        if (Directions.isEmptyDirection(rmDirId)) {
            Directions.removeDirection(rmDirId);
            resp.setHeader("Refresh", redirection);
            req.getRequestDispatcher("jsp/removed.jsp").forward(req, resp);


        } else {
            resp.setHeader("Refresh", redirection);
            req.getRequestDispatcher("jsp/notRemoved.jsp").forward(req, resp);
        }
    }
}
