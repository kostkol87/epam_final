package Service;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstactService {
    protected static final Logger log = Logger.getLogger(AbstactService.class);

    HttpServletRequest req;
    HttpServletResponse resp;
    HttpSession session;

    AbstactService(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
        session = req.getSession(true);
    }
}
