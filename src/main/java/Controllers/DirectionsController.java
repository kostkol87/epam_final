package Controllers;

import Service.DirectionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addDirection", "/removeDirection"})
public class DirectionsController extends HttpServlet{
    DirectionsService services;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processDirectionsTool(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processDirectionsTool(req, resp);
    }

    protected void processDirectionsTool(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        services = new DirectionsService(req, resp);

        switch (requestURI){
            case ("/addDirection"):{
                services.processAdded();
                break;
            }case ("/removeDirection"):{
                services.processRemoving();
                break;
            }
        }
    }
}
