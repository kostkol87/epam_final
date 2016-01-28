package Controllers;


import DAO.Utils.Directions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/addDirection")
public class AddDirection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processAdded(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processAdded(req, resp);
    }

    private void processAdded(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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


        } catch (ParseException e) {
            e.printStackTrace();
        }

        Directions.addDirection(fieldDeparture, depTime, fieldDestination, destTime, basicPrice, dateMult, fillMult, capacity);
        req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
    }
}
