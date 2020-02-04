package fr.uha.appintav.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	public @ResponseBody Integer add() {
		return this.colocationRepository.save(new Colocation()).getId();
	}
	
	public @ResponseBody Colocation addUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(userId).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + userId + "' does not exist.");
		}
		
		if (coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' already has user with id '" + userId + "'.");

		user.setColocation(coloc);
		this.userRepository.save(user);
		coloc.getUsers().add(user);
		return coloc;
	}
	
	public @ResponseBody Colocation removeUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(userId).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + userId + "' does not exist.");
		}
		if (!coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' doesn't has user with id '" + userId + "'.");

		user.setColocation(null);
		this.userRepository.save(user);
		coloc.getUsers().remove(user);
		return coloc;
	}
	
	public @ResponseBody Colocation addTask(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) Integer points,
			@RequestParam(required = true) Integer askerId) {
		Colocation coloc = null;
		User user = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(askerId).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + askerId + "' does not exist.");
		}
		
		Task task = this.taskRepository.save(new Task(description, points, coloc, user));
		this.taskRepository.save(task);
		return coloc;
	}
	
	public @ResponseBody Colocation taskDone(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer taskId,
			@RequestParam(required = true) Integer donerId) {
		Colocation coloc = null;
		User user = null;
		Task task = null;
		try {
			coloc = this.colocationRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		}
		try {
			user = this.userRepository.findById(donerId).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + donerId + "' does not exist.");
		}
		try {
			task = this.taskRepository.findById(taskId).get();
		}catch(NoSuchElementException e) {
			throw new RecordNotFoundException("Task with id '" + taskId + "' does not exist.");
		}
		
		if (!coloc.getTasks().contains(task))
			throw new RecordNotFoundException("Task with id '" + taskId + "' is not in colocation with id '" + id + "'.");
		if (!coloc.getUsers().contains(this.userRepository.findById(donerId).get()))
			throw new RecordNotFoundException("User with id '" + donerId + "' is not in colocation with id '" + id + "'.");

		for (User u : coloc.getUsers()) {
			if (u.getName().equals(user.getName())) {
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
	
	public @ResponseBody Colocation update(@RequestBody Colocation colocation) {
		return this.colocationRepository.save(colocation);
	}

	public @ResponseBody String deleteById(@RequestParam(value = "id", required = true) Integer id) {
		Optional<Colocation> colocOpt = this.colocationRepository.findById(id);
		if (colocOpt.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");	
		this.colocationRepository.deleteById(id);
		return "Deleted";
	}

	public @ResponseBody Iterable<Colocation> getAll() {
		return this.colocationRepository.findAll();
	}
	
}

