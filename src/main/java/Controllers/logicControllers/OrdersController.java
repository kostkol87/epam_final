package controllers.logicControllers;

import service.logicServices.OrdersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/newOrder", "/changeOrder", "/changeComplete", "/payment", "/payOk"})
public class OrdersController extends HttpServlet{
    OrdersService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processOrdersTool(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processOrdersTool(req, resp);
    }
    protected void processOrdersTool(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service = new OrdersService(req, resp);
        String requestURI = req.getRequestURI();

        switch (requestURI){
            case ("/newOrder"):{
                service.processNewOrder();
                break;
            }case ("/changeOrder"):{
                service.processChangeOrder();
                break;
            }case ("/changeComplete"):{
                service.processChangeComplete();
                break;
            }case ("/payment"):{
                service.processPayment();
                break;
            }case ("/payOk"):{
                service.processPaymentComplete();
                break;
            }
        }

    }

}
