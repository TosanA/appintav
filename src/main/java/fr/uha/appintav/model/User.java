package fr.uha.appintav.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
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
		return "User : { id: " + this.id +", name: " + this.name + " points: " + this.points + " }";
	}
	
	public User() {}
	
	public User(String name, int points) {
		this.name = name;
		this.setPoints(points);
		this.asker = new ArrayList<Task>();
		this.doner = new ArrayList<Task>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
