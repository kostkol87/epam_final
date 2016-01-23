package Controllers;

import DAObjects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLogin(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLogin(req, resp);
    }

    protected void processLogin(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        User sUser = (User) session.getAttribute("user");
        try{
            if (sUser != null) {
                session.setAttribute("page", 1);
                req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
