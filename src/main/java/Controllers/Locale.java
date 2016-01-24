package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/locale/*")
public class Locale extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    protected void processLocalization(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        try {
            switch (req.getRequestURI())
            {
                case "/locale/ru_RU": {
                    session.setAttribute("locale", "ru_RU");
                    resp.setHeader("Referer", "ru_RU");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                }
                case "/locale/en_US": {
                    session.setAttribute("locale", "en_US");
                    resp.setHeader("Referer", "en_US");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
