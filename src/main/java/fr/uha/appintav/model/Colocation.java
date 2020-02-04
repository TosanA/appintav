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
public class Colocation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@OneToMany(mappedBy = "colocation")
	private List<User> users;

	@OneToMany(mappedBy = "colocation")
	private List<Task> tasks;
	
	public Colocation() {
		this.users = new ArrayList<User>();
		this.tasks = new ArrayList<Task>();
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer i) {
		this.id = i;
	}
	
	public List<User> getUsers(){
		return this.users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Task> getTasks(){
		return this.tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
