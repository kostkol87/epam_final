package Controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDirection")
public class RemoveDirection extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRemoving(req, resp);
    }
    protected void processRemoving(HttpServletRequest req, HttpServletResponse resp){
//        int directionId =
        try {
            resp.getWriter().print("URAAAA");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
