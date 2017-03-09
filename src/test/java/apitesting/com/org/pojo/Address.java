package apitesting.com.org.pojo;

public class Address {
	Object street;
	Object suite;
	Object city;
	Object zipcode;
	Geo geo;

	
	
	public Address() {
	}

	public Address(Object street, Object suite, Object city,Object zipcode,Geo geo) {
		this.geo = geo;
		this.zipcode = zipcode;
		this.street = street;
		this.suite = suite;
		this.city = city;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public Object getZipcode() {
		return zipcode;
	}

	public void setZipcode(Object zipcode) {
		this.zipcode = zipcode;
	}

	public Object getStreet() {
		return street;
	}

	public void setStreet(Object street) {
		this.street = street;
	}

	public Object getSuite() {
		return suite;
	}

	public void setSuite(Object suite) {
		this.suite = suite;
	}

	public Object getCity() {
		return city;
	}

	public void setCity(Object city) {
		this.city = city;
	}

}
