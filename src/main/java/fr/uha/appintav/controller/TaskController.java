package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.uha.appintav.model.Task;
import fr.uha.appintav.repo.TaskRepository;

@Component
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	public @ResponseBody ResponseEntity<String> delete(@RequestParam(value = "id", required = true) Integer id) {
		this.taskRepository.deleteById(id);
		return new ResponseEntity<>("Task with id '" + id + "' deleted.", HttpStatus.OK);
	}

	public @ResponseBody Iterable<Task> getAllTasks() {
		return this.taskRepository.findAll();
	}
	
}

