package apitesting.com.org.jsonprocessor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apitesting.com.org.pojo.Address;
import apitesting.com.org.pojo.Company;
import apitesting.com.org.pojo.Geo;
import apitesting.com.org.pojo.User;

public class JsonProcessor {
    JSONParser parser;
    JSONArray jsonArray;
    
	public String JsonObjectProcessor(Object id, Object name, Object username, Object email,Object street, Object suite, Object city, Object zipcode, 
			Object lat, Object lng, Object phone, Object website,
			Object cname, Object catchPhrase, Object bs) throws JsonProcessingException{
     	
		User user = new User();
     	Company company = new Company();
     	Geo geo = new Geo();
     	Address address = new Address();
     	
     	company.setName(cname);
     	company.setCatchPhrase(catchPhrase);
     	company.setBs(bs);

     	geo.setLat(lat);
     	geo.setLng(lng);

     	address.setStreet(street);
     	address.setSuite(suite);
     	address.setCity(city);
     	address.setZipcode(zipcode);
     	address.setGeo(geo);

     	user.setId(id);
     	user.setName(name);
     	user.setUsername(username);
     	user.setEmail(email);
     	user.setPhone(phone);
     	user.setWebsite(website);
     	user.setAddress(address);
     	user.setCompany(company);
     	
        ObjectMapper mapper = new ObjectMapper();

        String value = mapper.writeValueAsString(user);
        return value;
	}

	public void JsonPrintUserName(JSONParser parser,String body){
		try {
			jsonArray = (JSONArray) parser.parse(body);
			for (int i = 0; i <= jsonArray.size() - 1; i++) {
				JSONObject jsonobj = (JSONObject) jsonArray.get(i);
				System.out.println("Full name of user: " + jsonobj.get("name"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassCastException c) {
			c.printStackTrace();
		}
	}
}
