package fr.uha.appintav.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.controller.ColocationController;
import fr.uha.appintav.model.Colocation;

@RestController
@RequestMapping("/colocations")
public class ColocationRoutes {
	
	@Autowired
	private ColocationController colocationController;
	
	@PostMapping(path="/add")
	public @ResponseBody Integer add() {
		return this.colocationController.add();
	}
	
	@PostMapping(path="/addUser")
	public @ResponseBody Colocation addUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String userEmail) {
		return this.colocationController.addUser(id, userEmail);
	}
	
	@DeleteMapping(path="/removeUser")
	public @ResponseBody Colocation removeUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String userEmail) {
		return this.colocationController.removeUser(id, userEmail);
	}
	
	@PostMapping(path="/addTask")
	public @ResponseBody Colocation addTask(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) Integer points,
			@RequestParam(required = true) String askerEmail) {
		return this.colocationController.addTask(id, description, points, askerEmail);
	}
	
	@PostMapping(path="/taskDone")
	public @ResponseBody Colocation taskDone(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer taskId,
			@RequestParam(required = true) String donerEmail) {
		return this.colocationController.taskDone(id, taskId, donerEmail);
	}
	
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteById(
			@RequestParam(value = "id", required = true) Integer id) {
		return this.colocationController.deleteById(id);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Colocation> getAll() {
		return this.colocationController.getAll();
	}

}
