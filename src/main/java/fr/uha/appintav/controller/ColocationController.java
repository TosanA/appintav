package fr.uha.appintav.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/colocations")
public class ColocationController {

	@Autowired
	private ColocationRepository colocationRepository;
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody Integer add() {
		return this.colocationRepository.save(new Colocation()).getId();
	}
	
	@PostMapping(path="/addUser")
	public @ResponseBody Colocation addUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		Optional<Colocation> colocOpt = this.colocationRepository.findById(id);
		if (colocOpt.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		Colocation coloc = colocOpt.get();
		
		Optional<User> userOpt = userRepository.findById(userId);
		User user = userOpt.get();
		
		if (coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' already has user with id '" + userId + "'.");
		
		coloc.getUsers().add(user);
		
		return this.colocationRepository.save(coloc);
	}
	
	@DeleteMapping(path="/removeUser")
	public @ResponseBody Colocation removeUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		User user = userRepository.findById(userId).get();
		Colocation coloc = this.colocationRepository.findById(id).get();
		
		if (!coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' doesn't has user with id '" + userId + "'.");
		
		coloc.getUsers().remove(user);
		return this.colocationRepository.save(coloc);
	}
	
	@PostMapping(path="/addTask")
	public @ResponseBody Colocation addTask(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) Integer points,
			@RequestParam(required = true) Integer askerId) {

		Colocation coloc = this.colocationRepository.findById(id).get();
		this.userRepository.findById(askerId);
		
		coloc.getTasks().add(this.taskRepository.save(new Task(description, points, id, askerId)));
		
		return this.colocationRepository.save(coloc);
	}
	
	@PostMapping(path="/taskDone")
	public @ResponseBody Colocation taskDone(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer taskId,
			@RequestParam(required = true) Integer donerId) {
		
		Colocation coloc = this.colocationRepository.findById(id).get();
		Task task = this.taskRepository.findById(taskId).get();
		
		if (!coloc.getTasks().contains(task))
			throw new RecordNotFoundException("Task with id '" + taskId + "' is not in colocation with id '" + id + "'.");
		if (!coloc.getUsers().contains(this.userRepository.findById(donerId).get()))
			throw new RecordNotFoundException("User with id '" + donerId + "' is not in colocation with id '" + id + "'.");

		task.setisDone(true);
		task.setDonerId(donerId);
		this.taskRepository.save(task);
		
		return coloc;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody Colocation update(@RequestBody Colocation colocation) {
		return this.colocationRepository.save(colocation);
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteById(@RequestParam(value = "id", required = true) Integer id) {
		Optional<Colocation> colocOpt = this.colocationRepository.findById(id);
		if (colocOpt.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");	
		this.colocationRepository.deleteById(id);
		return "Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Colocation> getAll() {
		return this.colocationRepository.findAll();
	}
	
}

