package Controllers;

import DAO.Entities.User;
import DAO.Utils.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRegister(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRegister(req, resp);
    }

    /**
     * getting all atributes from register.jsp and createst new {@link DAO.Entities.User}
     *
     * @param req
     * @param resp
     */
    protected void processRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User newUser = new User();
        String eMail = req.getParameter("loginField");

        if (eMail == null && session.getAttribute("user") == null) {
            session.setAttribute("page", 1);
            req.getRequestDispatcher("jsp/registerFail.jsp").forward(req, resp);
            return;

        } else if (session.getAttribute("user") != null) {
            session.setAttribute("page", 1);
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
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


        if (!Users.addUser(newUser)) {
            req.getRequestDispatcher("jsp/registerFail.jsp").forward(req, resp);
        } else {
            session.setAttribute("user", newUser);
            session.setAttribute("page", 1);
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
        }
    }
}
