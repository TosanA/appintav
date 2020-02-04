package fr.uha.appintav.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User implements Serializable {
	@Id
	private String email;
	private String password;
	
	private Integer points;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="colocationid")
	private Colocation colocation;
	
	@OneToMany(mappedBy = "asker")
	private List<Task> asker;
	
	@OneToMany(mappedBy = "doner")
	private List<Task> doner;
	
	
	@Override
	public String toString() {
		return "User : { email: " + this.email + " points: " + this.points + " }";
	}
	
	public User() {}
	
	public User(String email, String password, int points) {
		this.setEmail(email);
		this.setPassword(password);
		this.setPoints(points);
		this.asker = new ArrayList<Task>();
		this.doner = new ArrayList<Task>();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Colocation getColocation() {
		return colocation;
	}

	public void setColocation(Colocation colocation) {
		this.colocation = colocation;
	}
	
	public List<Task> getAsker(){
		return this.asker;
	}
	
	public void setAsker(List<Task> asker) {
		this.asker = asker;
	}
	
	public List<Task> getDoner(){
		return this.doner;
	}
	
	public void setDoner(List<Task> doner) {
		this.doner = doner;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
