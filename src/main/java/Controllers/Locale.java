package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/locale")
public class Locale extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    protected void processLocalization(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        HttpSession session = req.getSession(true);
        String loc = req.getParameter("loc");
        String target = req.getParameter("target");
        try {
            switch (loc) {
                case "ru_RU": {
                    session.setAttribute("locale", "ru_RU");
                    req.getRequestDispatcher(target).forward(req, resp);
                    break;
                }
                case "en_US": {
                    session.setAttribute("locale", "en_US");
                    req.getRequestDispatcher(target).forward(req, resp);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
