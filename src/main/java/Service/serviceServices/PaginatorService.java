package service.serviceServices;


import dataBase.DAO.Directions;
import JSTL.DirectionsTag;
import service.AbstractService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaginatorService extends AbstractService {
    public PaginatorService(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void processPaginate() throws ServletException, IOException {
        Directions directions;

        String moveTo = req.getParameter("fwd");
        String target = req.getParameter("target");

        HttpSession session = req.getSession(true);

        switch (moveTo) {
            case "r": {
                directions = new Directions();

                int directionsCount = directions.getDirections().size();
                int page = (int) session.getAttribute("page");
                int pagesCount = directionsCount / DirectionsTag.ON_PAGE;

                boolean hasTail = (directionsCount % DirectionsTag.ON_PAGE) != 0;

                if ((page <= pagesCount && hasTail) | (page < pagesCount && !hasTail)) {
                    session.setAttribute("page", ++page);
                    req.getRequestDispatcher(target).forward(req, resp);

                } else {
                    req.getRequestDispatcher(target).forward(req, resp);
                }
                break;
            }
            case "l": {
                int page = (int) session.getAttribute("page");

                if (page > 1) {
                    session.setAttribute("page", --page);
                    req.getRequestDispatcher(target).forward(req, resp);
                } else {
                    req.getRequestDispatcher(target).forward(req, resp);
                }
                break;
            }
        }
    }
}
