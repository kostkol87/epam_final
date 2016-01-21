package Controllers;

import DAObjects.EntityCollections;
import DAObjects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logged")
public class VerificationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processVerify(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processVerify(req, resp);
    }

    protected void processVerify(HttpServletRequest req, HttpServletResponse resp) {
        try {
                String login = (String) req.getParameter("loginField");
                String password = (String) req.getParameter("passField");
            if (login == null || password == null){
                req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
            }

                System.out.println(login + "   " + password);
                User user = new EntityCollections().getUser(login);


                if (password.equals(user.getPassword())) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("jsp/loginFail.jsp").forward(req, resp);
                }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
