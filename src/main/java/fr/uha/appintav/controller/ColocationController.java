package fr.uha.appintav.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.uha.appintav.error.RecordAlreadyExistsException;
import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.Colocation;
import fr.uha.appintav.model.Task;
import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.ColocationRepository;
import fr.uha.appintav.repo.TaskRepository;
import fr.uha.appintav.repo.UserRepository;

@Component
public class ColocationController {

	@Autowired
	private ColocationRepository colocationRepository;
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Integer add() {
		return this.colocationRepository.save(new Colocation()).getId();
	}
	
	public Colocation addUser(Integer id, String email) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(email).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + email + "' does not exist.");
		}
		
		if (coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' already has user with email '" + email + "'.");

		user.setColocation(coloc);
		this.userRepository.save(user);
		coloc.getUsers().add(user);
		return coloc;
	}
	
	public Colocation removeUser(Integer id, String email) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(email).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with email '" + email + "' does not exist.");
		}
		if (!coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' doesn't has user with id '" + email + "'.");

		user.setColocation(null);
		this.userRepository.save(user);
		coloc.getUsers().remove(user);
		return coloc;
	}
	
	public Colocation addTask(Integer id, String description, Integer points, String askerEmail) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(askerEmail).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with email '" + askerEmail + "' does not exist.");
		}
		
		Task task = this.taskRepository.save(new Task(description, points, coloc, user));
		this.taskRepository.save(task);
		return coloc;
	}
	
	public Colocation taskDone(Integer id, Integer taskId, String donerEmail) {
		Colocation coloc = null;
		User user = null;
		Task task = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(donerEmail).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with email '" + donerEmail + "' does not exist.");
		}
		try {
			task = this.taskRepository.findById(taskId).get();
		}catch(NoSuchElementException e) {
			throw new RecordNotFoundException("Task with id '" + taskId + "' does not exist.");
		}
		
		if (!coloc.getTasks().contains(task))
			throw new RecordNotFoundException("Task with id '" + taskId + "' is not in colocation with id '" + id + "'.");
		if (!coloc.getUsers().contains(this.userRepository.findById(donerEmail).get()))
			throw new RecordNotFoundException("User with id '" + donerEmail + "' is not in colocation with id '" + id + "'.");

		for (User u : coloc.getUsers()) {
			if (u.getEmail().equals(user.getEmail())) {
				u.setPoints(u.getPoints() + task.getPoints());
			}else {
				u.setPoints(u.getPoints() - task.getPoints() / (coloc.getUsers().size() - 1));
			}
			this.userRepository.save(u);
		}
		task.setisDone(true);
		task.setDoner(user);
		this.taskRepository.save(task);
		
		return coloc;
	}
	
	public Colocation update(Colocation colocation) {
		return this.colocationRepository.save(colocation);
	}

	public ResponseEntity<String> deleteById(Integer id) {
		Optional<Colocation> colocOpt = this.colocationRepository.findById(id);
		if (colocOpt.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");	
		for(User user : colocOpt.get().getUsers()) {
			this.userRepository.delete(user);
		}
		for(Task task : colocOpt.get().getTasks()) {
			this.taskRepository.delete(task);
		}
		this.colocationRepository.deleteById(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

	public Iterable<Colocation> getAll() {
		return this.colocationRepository.findAll();
	}
	
}

