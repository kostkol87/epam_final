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

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRegister(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRegister(req, resp);
    }

    protected void processRegister(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        User newUser = new User();
        try {
            String eMail = req.getParameter("loginField");
            if (eMail == null) {
                req.getRequestDispatcher("jsp/registerFail.jsp").forward(req, resp);
                return;
            }
            String password = req.getParameter("passField");
            String surname = req.getParameter("sNameField");
            String name = req.getParameter("nameField");
            String patronomic = req.getParameter("patronomicField");

            newUser.setEmail(eMail);
            newUser.setPassword(password);
            newUser.setSurname(surname);
            newUser.setName(name);
            newUser.setPatronomic(patronomic);


            if (!new EntityCollections().addUser(newUser)) {
                req.getRequestDispatcher("jsp/registerFail.jsp").forward(req, resp);
            } else {
                session.setAttribute("user", newUser);
                req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
