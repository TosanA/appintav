package fr.uha.appintav.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Embeddable
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	private Integer points;

	//@ManyToOne
	//@JoinColumn(name="COLOC_ID", updatable = false, insertable = false)
	//private Colocation colocation;
	
	@Override
	public String toString() {
		return "User : { id: " + this.id +", name: " + this.name + " points: " + this.points + " }";
	}
	
	public User() {}
	
	public User(String name, int points) {
		this.name = name;
		this.setPoints(points);
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
/*
	public Colocation getColocation() {
		return colocation;
	}

	public void setColocation(Colocation colocation) {
		this.colocation = colocation;
		if (!this.colocation.getUsers().contains(this))
			this.colocation.getUsers().add(this);
	}*/
}
