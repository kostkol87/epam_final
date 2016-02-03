package Controllers;

import Service.PaginatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class PaginatorController extends HttpServlet{
    PaginatorService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPaginate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPaginate(req, resp);
    }

    protected void processPaginate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        new PaginatorService(req, resp).processPaginate();
    }
}
