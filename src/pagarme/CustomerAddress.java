package pagarme;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class CustomerAddress  implements Serializable{

	private static final long serialVersionUID = -5381987709189927845L;
	private String id;
	private String city;
	private String complementary;
	private String country;
	private String neighborhood;
	private String number;
	private String state;
	private String street;
	private String zipcode;
	
	@SuppressWarnings("unused")
	private boolean freezed;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getComplementary() {
		return complementary;
	}
	public void setComplementary(String complementary) {
		this.complementary = complementary;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	private void readObject(ObjectInputStream ois)	throws ClassNotFoundException, IOException {
		  ois.defaultReadObject();
		  freezed = true;
	}
}
