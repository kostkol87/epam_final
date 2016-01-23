package Controllers;

import DAObjects.EntityCollections;
import JSTL.DirectionsTag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/page/*")
public class Paginator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPaginate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPaginate(req, resp);
    }

    protected void processPaginate(HttpServletRequest req, HttpServletResponse resp){
        String moveTo = req.getRequestURI();
        HttpSession session = req.getSession(true);
        try {
            switch (moveTo) {
                case "/page/r": {
                    int page = (int) session.getAttribute("page");
                    int pagesCount = EntityCollections.directionsCount / DirectionsTag.ON_PAGE;
                    boolean hasTail = (EntityCollections.directionsCount % DirectionsTag.ON_PAGE) != 0;

                    if (page <= pagesCount && hasTail) {
                        session.setAttribute("page", ++page);
                        resp.sendRedirect(req.getHeader("Referer"));

                    }else if (page < pagesCount && !hasTail) {
                        session.setAttribute("page", ++page);
                        resp.sendRedirect(req.getHeader("Referer"));

                    }else {
                        resp.sendRedirect(req.getHeader("Referer"));
                    }
                    break;
                }
                case "/page/l": {
                    int page = (int) session.getAttribute("page");
                    if (page>1) {
                        session.setAttribute("page", --page);
                        resp.sendRedirect(req.getHeader("Referer"));
                    }else {
                        resp.sendRedirect(req.getHeader("Referer"));
                    }
                    break;
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
