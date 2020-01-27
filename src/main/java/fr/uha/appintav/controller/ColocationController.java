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
import fr.uha.appintav.repo.UserRepository;
import fr.uha.appintav.service.ColocationService;
import fr.uha.appintav.service.TaskService;
import fr.uha.appintav.service.UserService;

@RestController
@RequestMapping("/colocations")
public class ColocationController {

	@Autowired
	private ColocationService colocationService;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping(path="/add")
	public @ResponseBody Integer add() {
		return this.colocationService.save(new Colocation()).getId();
	}
	
	@PostMapping(path="/addUser")
	public @ResponseBody Colocation addUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		User user = userService.findById(userId);
		Colocation coloc = this.colocationService.findById(id);
		
		if (coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' already has user with id '" + userId + "'.");
		
		coloc.getUsers().add(user);
		return this.colocationService.save(coloc);
	}
	
	@DeleteMapping(path="/removeUser")
	public @ResponseBody Colocation removeUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		User user = userService.findById(userId);
		Colocation coloc = this.colocationService.findById(id);
		
		if (!coloc.getUsers().contains(user))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' doesn't has user with id '" + userId + "'.");
		
		coloc.getUsers().remove(user);
		return this.colocationService.save(coloc);
	}
	
	@PostMapping(path="/addTask")
	public @ResponseBody Colocation addTask(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) Integer points,
			@RequestParam(required = true) Integer askerId) {

		Colocation coloc = this.colocationService.findById(id);
		this.userService.findById(askerId);
		
		coloc.getTasks().add(this.taskService.save(new Task(description, points, id, askerId)));
		
		return this.colocationService.save(coloc);
	}
	
	@PostMapping(path="/taskDone")
	public @ResponseBody Colocation taskDone(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer taskId,
			@RequestParam(required = true) Integer donerId) {
		
		Colocation coloc = this.colocationService.findById(id);
		Task task = this.taskService.findById(taskId);
		
		if (!coloc.getTasks().contains(task))
			throw new RecordNotFoundException("Task with id '" + taskId + "' is not in colocation with id '" + id + "'.");
		if (!coloc.getUsers().contains(this.userService.findById(donerId)))
			throw new RecordNotFoundException("User with id '" + donerId + "' is not in colocation with id '" + id + "'.");

		task.setisDone(true);
		task.setDonerId(donerId);
		this.taskService.save(task);
		
		return coloc;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody Colocation update(@RequestBody Colocation colocation) {
		return this.colocationService.save(colocation);
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		Colocation coloc = this.colocationService.findById(id);
		
		this.colocationService.deleteById(id);
		return "Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Colocation> getAll() {
		return this.colocationService.findAll();
	}
	
}

