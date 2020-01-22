package fr.uha.appintav.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Colocation {
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private List<User> users;
}
