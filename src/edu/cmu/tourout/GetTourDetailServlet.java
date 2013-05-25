package edu.cmu.tourout;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class GetTourDetailServlet extends HttpServlet {

	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get ID from request parameter
		String id = request.getParameter("id");
		
		try {
			// Query datastore to get the tour
			Key key = KeyFactory.createKey("Tour", Long.parseLong(id));
			Entity entity = datastore.get(key);
			
	        // Create json and print to response
	        response.setContentType("text/json");
	        Gson gson = new Gson();
	        response.getWriter().println(gson.toJson(entity));
		}
		catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
