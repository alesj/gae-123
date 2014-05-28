package org.openblend.gae123;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class HelloServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");

        if (name != null) {
            log.info(String.format("[LOG] name = %s", name));
        }

        resp.getWriter().write("Hello " + name + "!");
    }
}
