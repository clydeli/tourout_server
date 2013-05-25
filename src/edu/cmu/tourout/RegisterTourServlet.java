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

public class RegisterTourServlet extends HttpServlet {

    private static final long serialVersionUID = -6745910217120664538L;

    private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newVisitor = request.getParameter("visitor");
        Key key = KeyFactory.stringToKey(request.getParameter("key"));
        try {
            Entity entity = datastore.get(key);
            String visitorList = entity.getProperty("visitors").toString();
            String[] visitors = visitorList.split(",");
            for (String existingVisitor : visitors) {
                if (existingVisitor.equals(newVisitor)) {
                    return;
                }
            }
            visitorList = visitorList + newVisitor;
            entity.setProperty("visitors", visitorList);
            datastore.put(entity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
