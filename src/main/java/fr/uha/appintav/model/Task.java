package fr.uha.appintav.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String description;
	private Integer points;
	private boolean isDone;
	
	public Task() {}
	
	public Task(String description, Integer points) {
		this.description = description;
		this.points = points;
		this.isDone = false;
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

}
