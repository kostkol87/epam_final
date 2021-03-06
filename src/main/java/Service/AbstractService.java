package service;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractService {
    protected static final Logger log = Logger.getLogger(AbstractService.class);

    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    protected HttpSession session;

    protected AbstractService(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
        session = req.getSession(true);
    }
}
