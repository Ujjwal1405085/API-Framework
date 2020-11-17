package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace AddPlacePayload(String Name, String Language, String Address) {

		AddPlace p = new AddPlace();
		p.setAccuracy(70);
		p.setAddress(Address);
		p.setLanguage(Language);
		p.setName(Name);
		p.setPhone_number("2353424234");
		p.setWebsite("google.com");
		
		//list
		List<String> MyList = new ArrayList<String>();
		MyList.add("ujjwal");MyList.add("Deepti");
		p.setTypes(MyList);
		
		//Loction sub Class
		Location l = new Location();
		l.setLat(34.4535345);
		l.setLng(-34.23523524);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";
	}
}
