package fr.uha.appintav.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Address implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer number;
	private String street;
	private Integer postalCode;
	private String city;

	@OneToMany(mappedBy = "address")
	private List<Colocation> colocation;

	@Override
	public String toString() {
		return "Address : { id: " + this.id + " number: " + this.number + " street: "+this.street+" postal code: "+this.postalCode+" city: "+this.city + " }";
	}

	public Address() {}

	public Address(Integer number, String street, Integer postalCode, String city) {
			this.setNumber(number);
			this.setStreet(street);
			this.setPostalCode(postalCode);
			this.setCity(city);
			this.colocation = new ArrayList<Colocation>();
		}

	public Integer getId() {
		return this.id;
	}

	public void setEmail(Integer id) {
		this.id = id;
	}
	
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Colocation> getColocation() {
		return this.colocation;
	}

	public void setColocation(List<Colocation> colocation) {
		this.colocation = colocation;
	}

}
