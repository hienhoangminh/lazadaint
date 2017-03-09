package apitesting.com.org.pojo;

public class User {

	Object id;
	Object name;
	Object username;
	Object email;
	Address address;
	Object phone;
	Object website;
	Company company;
	
	public User() {
	}

	public User(Object id, Object name, Object username, Object email, Address address, Object phone, Object website,
			Company company) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getUsername() {
		return username;
	}

	public void setUsername(Object username) {
		this.username = username;
	}

	public Object getEmail() {
		return email;
	}

	public void setEmail(Object email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Object getPhone() {
		return phone;
	}

	public void setPhone(Object phone) {
		this.phone = phone;
	}

	public Object getWebsite() {
		return website;
	}

	public void setWebsite(Object website) {
		this.website = website;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
