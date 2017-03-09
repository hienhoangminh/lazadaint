package apitesting.com.org.pojo;

public class Company {

	Object name;
	Object catchPhrase;
	Object bs;

	
	
	public Company() {
	}

	public Company(Object name, Object catchPhrase, Object bs) {
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(Object catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public Object getBs() {
		return bs;
	}

	public void setBs(Object bs) {
		this.bs = bs;
	}

}
