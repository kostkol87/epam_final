package Controllers;


import Service.AuthServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout", "/register", "/logged"})
public class AuthController extends HttpServlet {
    AuthServices services;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processAuth(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processAuth(req, resp);
    }

    protected void processAuth(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        services = new AuthServices(req, resp);

        switch (requestURI){
            case ("/login"):{
                services.processLogin();
                break;
            }case ("/logout"):{
                services.processLogout();
                break;
            }case ("/register"):{
                services.processRegister();
                break;
            }case ("/logged"):{
                services.processValidate();
                break;
            }
        }
    }
}
