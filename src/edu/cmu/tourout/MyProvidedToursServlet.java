package edu.cmu.tourout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class MyProvidedToursServlet extends HttpServlet {

	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get provider parameter
		String provider = request.getParameter("provider");
		
		// Query datastore to get all tours with given name as a provider
		Query query = new Query("Tour");
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterator();
        List<Entity> results = new ArrayList<Entity>();
        while (entities.hasNext()) {
            Entity entity = entities.next();
            String visitors = (String) entity.getProperty("provider");
            if (visitors.contentEquals(provider)) {
            	results.add(entity);
            }
        }
        
        // Create json and print to response
        response.setContentType("text/json");
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(results));
		
	}
	
}
