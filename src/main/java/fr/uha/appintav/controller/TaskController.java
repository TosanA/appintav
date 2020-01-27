package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.model.Task;
import fr.uha.appintav.repo.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@DeleteMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		this.taskRepository.deleteById(id);
		return "Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Task> getAllTasks() {
		return this.taskRepository.findAll();
	}
	
}

