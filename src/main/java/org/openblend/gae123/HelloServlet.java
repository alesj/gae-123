package org.openblend.gae123;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
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
        final String level = req.getParameter("level");

        List<Level> levels;
        if (level != null) {
            levels = Collections.singletonList(Level.parse(level));
        } else {
            levels = Arrays.asList(Level.FINE, Level.INFO, Level.WARNING, Level.SEVERE);
        }

        if (name != null) {
            for (Level ll : levels) {
                log.log(ll, String.format("[LOG - %s] name = %s", ll, name));
            }
        }

        resp.getWriter().write("Hello " + name + "!");
    }
}
