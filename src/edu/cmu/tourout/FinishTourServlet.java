package edu.cmu.tourout;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class FinishTourServlet extends HttpServlet {

    private static final long serialVersionUID = -3670689032501372821L;

    private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Key key = KeyFactory.stringToKey(request.getParameter("key"));
        try {
            Entity entity = datastore.get(key);
            entity.setProperty("isFinished", true);
            response.setContentType("text/html");
            response.getWriter().println("");
        }
        catch (Exception e) {
            
        }
    }
}
