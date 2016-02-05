package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AppServerUtils {

    static <T> T localJndiSearch(String name, Class<T> aClass) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            Object o = envContext.lookup(name);
            if (aClass.isInstance(o))
                return (T) o;
            else
                throw new NamingException("Object, founded by " + name + " name is not an instance of class " + aClass.getName());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
