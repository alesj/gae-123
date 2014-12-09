package org.openblend.gae123;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class HelloServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    private static final String KIND = "mvm_kind";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        String action = req.getParameter("action");
        if (action == null) {
            action = "put";
        }

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        if ("get".equalsIgnoreCase(action)) {
            PreparedQuery pq = service.prepare(new Query(KIND).addSort("ts", Query.SortDirection.DESCENDING));
            for (Entity entity : pq.asIterable()) {
                resp.getWriter().write(String.valueOf(entity));
            }
        } else if ("put".equalsIgnoreCase(action)) {
            Entity entity = new Entity(KIND);
            entity.setProperty("name", name);
            entity.setProperty("ts", System.currentTimeMillis());
            service.put(entity);
            resp.getWriter().write(String.valueOf(entity));
        }

/*
        if (name != null) {
            final String level = req.getParameter("level");

            List<Level> levels;
            if (level != null) {
                levels = Collections.singletonList(Level.parse(level));
            } else {
                levels = Arrays.asList(Level.FINE, Level.INFO, Level.WARNING, Level.SEVERE);
            }

            for (Level ll : levels) {
                log.log(ll, String.format("[LOG - %s] name = %s", ll, name));
            }

            resp.getWriter().write("Hello " + name + "!");
        }
*/
    }
}
