package fr.uha.appintav.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Task implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String description;
	private Integer points;
	private boolean isDone;
	
	@ManyToOne
	@JoinColumn(name = "colocation")	
	@JsonBackReference
	private Colocation colocation;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "asker")	
	private User asker;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "doner")	
	private User doner;
	
	public Task() {}
	
	public Task(String description, Integer points, Colocation colocation, User asker) {
		this.description = description;
		this.points = points;
		this.isDone = false;
		this.setColocation(colocation);
		this.setAsker(asker);
		this.setDoner(null);
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer i) {
		this.id = i;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public Integer getPoints() {
		return this.points;
	}
	
	public void setPoints(Integer p) {
		this.points = p;
	}
	
	public boolean getIsDone() {
		return this.isDone;
	}
	
	public void setisDone(boolean d) {
		this.isDone = d;
	}

	public User getAsker() {
		return asker;
	}

	public void setAsker(User asker) {
		this.asker = asker;
	}

	public User getDoner() {
		return doner;
	}

	public void setDoner(User doner) {
		this.doner = doner;
	}

	public Colocation getColocation() {
		return colocation;
	}

	public void setColocation(Colocation colocation) {
		this.colocation = colocation;
	}

}
