package edu.cmu.tourout;

import java.util.HashMap;
import java.util.Map;

public class CompanyLocations {

	private static Map<String, String> locationMap;
	static {
		locationMap  = new HashMap<String, String>();
		locationMap.put("Google", "37.422219,-122.083983");
		locationMap.put("Facebook", "37.483236,-122.150073");
		locationMap.put("Apple", "37.331693,-122.03021");
		locationMap.put("Twitter", "37.776753,-122.417135");
		locationMap.put("Microsoft", "37.411687,-122.071452");
		locationMap.put("Cisco", "37.411994,-121.932278");
		locationMap.put("Samsung", "37.368656,-121.913502");
		locationMap.put("Intel", "37.388061,-121.962962");
	}
	
	// return coordinates of company
	// default: 37.388061,-121.962962
	public static String getCoordinates(String companyName) {
		String coordinates = locationMap.get(companyName); 
		return coordinates != null ? coordinates : "37.388061,-121.962962"; 
	}
}
