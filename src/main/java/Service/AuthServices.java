package Service;


import DataBase.DAO.Users;
import DataBase.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServices extends AbstactService{

    public AuthServices(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void processLogin() throws ServletException, IOException {
        session.setAttribute("expired", "onlogin");
        User sUser = (User) session.getAttribute("user");
        if (sUser != null) {
            session.setAttribute("page", 1);
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }
    }

    public void processLogout() throws ServletException, IOException {
        session.invalidate();
        req.getRequestDispatcher("jsp/welcome.jsp").forward(req, resp);
    }

    public void processRegister() throws ServletException, IOException {
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

    public void processValidate() throws ServletException, IOException {
        String login = (String) req.getParameter("loginField");
        String password = (String) req.getParameter("passField");
        if (login == null || password == null){
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
        }

        User user = Users.getUser(login);

        if (password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            session.setAttribute("page", 1);
            req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/loginFail.jsp").forward(req, resp);
        }
    }
}
