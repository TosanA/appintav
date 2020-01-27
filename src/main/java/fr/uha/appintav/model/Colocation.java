package fr.uha.appintav.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "colocations")
public class Colocation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "COLOC_ID", updatable = false, insertable = false)
	//@org.hibernate.annotations.IndexColumn(name = "parent_index")
	@ElementCollection
	private List<User> users;

	@ElementCollection
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
