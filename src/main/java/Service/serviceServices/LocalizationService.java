package service.serviceServices;

import service.AbstractService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationService extends AbstractService {
    public LocalizationService(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void processLocale() throws ServletException, IOException {
        String loc = req.getParameter("loc");
        String target = req.getParameter("target");
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
    }
}
