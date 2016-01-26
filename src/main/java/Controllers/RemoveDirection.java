package Controllers;


import DAObjects.EntitiesUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDirection")
public class RemoveDirection extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }
    protected void processRemoving(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int rmDirId = Integer.valueOf(req.getParameter("id"));
        if(EntitiesUtils.isEmptyDirection(rmDirId)){
            /**
             * TODO remove it
             */
        }else {
            /**
             * TODO alert that there are passangers who have already bought tickets!
             */
        }
    }
}
