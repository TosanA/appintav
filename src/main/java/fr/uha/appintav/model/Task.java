package fr.uha.appintav.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Embeddable
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String description;
	private Integer points;
	private boolean isDone;
	
	private Integer colocationId;
	private Integer askerId;
	private Integer donerId;
	
	public Task() {}
	
	public Task(String description, Integer points, Integer colocationId, Integer askerId) {
		this.description = description;
		this.points = points;
		this.isDone = false;
		this.setColocationId(colocationId);
		this.setAskerId(askerId);
		this.setDonerId(null);
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

	public Integer getAskerId() {
		return askerId;
	}

	public void setAskerId(Integer askerId) {
		this.askerId = askerId;
	}

	public Integer getDonerId() {
		return donerId;
	}

	public void setDonerId(Integer donerId) {
		this.donerId = donerId;
	}

	public Integer getColocationId() {
		return colocationId;
	}

	public void setColocationId(Integer colocationId) {
		this.colocationId = colocationId;
	}

}
