package apitesting.com.org.pojo;

public class Geo {

	Object lat;
	Object lng;

	public Geo() {
	}

	public Geo(Object lat, Object lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public Object getLat() {
		return lat;
	}

	public void setLat(Object lat) {
		this.lat = lat;
	}

	public Object getLng() {
		return lng;
	}

	public void setLng(Object lng) {
		this.lng = lng;
	}

}
