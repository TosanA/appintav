package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.uha.appintav.model.Task;
import fr.uha.appintav.repo.TaskRepository;

@Component
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		this.taskRepository.deleteById(id);
		return "Task with id '" + id + "' deleted.";
	}

	public @ResponseBody Iterable<Task> getAllTasks() {
		return this.taskRepository.findAll();
	}
	
}

