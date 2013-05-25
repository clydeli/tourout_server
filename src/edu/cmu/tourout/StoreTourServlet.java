package edu.cmu.tourout;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class StoreTourServlet extends HttpServlet {

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get parameters from request
		String companyName = request.getParameter("companyName");
		String provider = request.getParameter("provider");
		Date meetingTime = new Date(Long.parseLong(request.getParameter("meetingTime")));
		Boolean haveFreeLunch = request.getParameter("haveFreeLunch").equalsIgnoreCase("true");
				
		// Create a Tour entity with properties
		Entity tour = new Entity("Tour");
		tour.setProperty("companyName", companyName);
		tour.setProperty("provider", provider);
		tour.setProperty("meetingTime", meetingTime);
		tour.setProperty("haveFreeLunch", haveFreeLunch);
		tour.setProperty("isFinished", false);
		tour.setProperty("visitors", "");
		tour.setProperty("coordinates", CompanyLocations.getCoordinates(companyName));
		
		// Put the entity to the datastore
		datastore.put(tour);
		
		/*
		// testing
		response.setContentType("text/html");
		response.getWriter().println("Company name: " + companyName);
		response.getWriter().println("Provider: " + provider);
		response.getWriter().println("Meeting time: " + meetingTime);
		response.getWriter().println("Have free lunch: " + haveFreeLunch);
		*/
		
	}
	
}
