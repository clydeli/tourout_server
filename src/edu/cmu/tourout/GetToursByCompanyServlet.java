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
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class GetToursByCompanyServlet extends HttpServlet {

	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get company name from request parameter
		String companyName = request.getParameter("companyName");
		
		// Query datastore to get all tours by the company
		Filter companyFilter = new FilterPredicate("companyName", FilterOperator.EQUAL, companyName);
		Filter finishedFilter = new FilterPredicate("isFinished", FilterOperator.EQUAL, false);
		Filter compositeFilter = CompositeFilterOperator.and(companyFilter, finishedFilter);
		Query query = new Query("Tour").setFilter(compositeFilter);
		PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterator();        
        List<Entity> results = new ArrayList<Entity>();
        while (entities.hasNext()) {
            Entity entity = entities.next();
            results.add(entity);
        }
        
        // Create json and print to response
        response.setContentType("text/json");
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(results));
	}
	
}
