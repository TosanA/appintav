package fr.uha.appintav.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.controller.TaskController;
import fr.uha.appintav.model.Task;

@RestController
@RequestMapping("/tasks")
public class TaskRoutes {
	
	@Autowired
	private TaskController taskController;
	
	@DeleteMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		return this.taskController.delete(id);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Task> getAllTasks() {
		return this.taskController.getAllTasks();
	}

}
