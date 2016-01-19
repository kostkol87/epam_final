package Controller;

import DAObjects.EntityCollections;
import DAObjects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class RootController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * TODO forward to certain page via requestDispatcher
         */
        HttpSession session = req.getSession(true);
        if (session.getAttribute("user") != "" && session.getAttribute("user") != null) {
            /**
             * TODO forward to users home page
             */
            System.out.println(session.getAttribute("user") + " user atribut");
        } else {
            /**
             * TODO go to login or registration (welcome page)
             */
            switch (req.getRequestURI()) {
                case "/login": {
                    req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                    break;
                }
                case "/register": {
                    req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
                    break;
                }
                case "/ru_RU": {
                    session.setAttribute("locale", "ru_RU");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                }
                case "/en_US": {
                    session.setAttribute("locale", "en_US");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                }
                case "/logged": {
                        switch (req.getHeader("Referer")) {
                            /**
                             * TODO validate log/pass and forward to workspace and save data to session or show error page
                             */
                            case "/login": {
                                System.out.println("come from login");
                                String login = (String) req.getAttribute("loginField");
                                String password = (String) req.getAttribute("passField");
                                User user = new EntityCollections().getUser(login);
                                if (password.equals(user.getPassword())) {
                                    session.setAttribute("userMail", user);
                                    req.getRequestDispatcher("jsp/workspace.jsp").forward(req, resp);
                                } else {
                                    req.getRequestDispatcher("jsp/loginFail.jsp").forward(req, resp);
                                }
                                break;
                            }
                            case "/register": {
                                /**
                                 *TODO add new user to database, save data to session and forward to workspace
                                 */
                                System.out.println("come from register");
                                req.getRequestDispatcher("jsp/loginFail.jsp").forward(req, resp);
                                break;
                            }
                        }
                    break;
                }
                default: {
                    req.getRequestDispatcher("jsp/welcome.jsp").forward(req, resp);
                }

            }

        }
    }
}
