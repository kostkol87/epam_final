package Service;

import DataBase.DAO.Directions;
import DataBase.Entities.Direction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class DirectionsService extends AbstractService {
    public DirectionsService(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void processAdded() throws ServletException, IOException {
        auth();
        String fieldDeparture = req.getParameter("fieldDeparture");
        String fieldDestination = req.getParameter("fieldDestination");

        int capacity = 0;

        Date depTime = null;
        Date destTime = null;

        double basicPrice = 0;
        double dateMult = 0;
        double fillMult = 0;

        try {
            capacity = Integer.parseInt(req.getParameter("capacity"));

            String strDepTime = (req.getParameter("depTime").replace("T", " ") + ":00");
            depTime = Directions.SDF.parse(strDepTime);

            String strDespTime = (req.getParameter("destTime").replace("T", " ") + ":00");
            destTime = Directions.SDF.parse(strDespTime);

            basicPrice = Double.parseDouble(req.getParameter("basicPrice"));
            dateMult = Double.parseDouble(req.getParameter("dateMult"));
            fillMult = Double.parseDouble(req.getParameter("fillMult"));


        } catch (ParseException | NumberFormatException e) {
            log.warn("direction addiction form filling error!");
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
            return;
        }

        Directions.addDirection(fieldDeparture, depTime, fieldDestination, destTime, basicPrice, dateMult, fillMult, capacity);
        req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
    }

    public void processRemoving() throws ServletException, IOException {
        auth();
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

    public static double getActualPrice(Direction direction) {
        /**
         * if there is less than 30 days on departure, price become bigger
         * if there are less than 50% places aboard lef, price become bigger
         */
        final int DAY = 1000 * 60 * 60 * 24;
        long today = new Date().getTime();
        double basicPrice = direction.getBasicPrice();
        double price = basicPrice;
        if ((direction.getDepTime().getTime() - today) / DAY < 30) {
            price += (direction.getDateMultiplier() - 1) * basicPrice;

        }
        if ((double) direction.getLeftPlaces() / (double) direction.getCapacity() < 0.5) {
            price += (direction.getFillMultiplier() - 1) * basicPrice;
        }
        return Math.round(price);
    }
}
