package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class RootController extends HttpServlet{
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
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
//        PrintWriter writer = resp.getWriter();
//        Connection connection = ConnectionPool.getInstance().getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT id, role FROM roles;");
//            while (resultSet.next()){
//                writer.print("id: " + resultSet.getString("id"));
//                writer.print("role: " + resultSet.getString("role"));
//            }
//            resultSet.close();
//            ConnectionPool.getInstance().free(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        HttpSession session = req.getSession(true);
        if (session.getAttribute("user") != "" && session.getAttribute("user") != null){
            /**
             * TODO forward to users home page
             */
            System.out.println(session.getAttribute("user") + " user atribut");
        }else {
            /**
             * TODO go to login or registration (welcome page)
             */
            switch (req.getRequestURI()){
                case "/login":{
                    req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                    break;
                }case "/register": {
                    req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
                    break;
                }case "/ru_RU": {
                    session.setAttribute("locale", "ru_RU");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                }case "/en_US": {
                    session.setAttribute("locale", "en_US");
                    resp.sendRedirect(req.getHeader("Referer"));
                    break;
                } default:{
                    req.getRequestDispatcher("jsp/welcome.jsp").forward(req, resp);
                }
            }

        }

    }
}
