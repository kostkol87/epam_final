package controllers.serviceControllers;

import service.serviceServices.LocalizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class LocaleController extends HttpServlet{
    LocalizationService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLocalization(req, resp);
    }

    protected void processLocalization(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        new LocalizationService(req, resp).processLocale();

    }
}
