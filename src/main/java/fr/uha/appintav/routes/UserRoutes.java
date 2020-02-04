package fr.uha.appintav.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.controller.UserController;
import fr.uha.appintav.model.User;

@RestController
@RequestMapping("/users")
public class UserRoutes {
	
	@Autowired
	private UserController userController;
	
	@PostMapping(path="/add")
	public @ResponseBody User add(
			@RequestParam(required = true) String name,
			@RequestParam(required = true) Integer points) {
		return this.userController.add(name, points);
	}
	
	@PostMapping(path="/update")
	public @ResponseBody User update(@RequestBody User user) {
		return this.userController.update(user);
	}
	
	@DeleteMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		return this.userController.delete(id);
	}
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return this.userController.getAllUsers();
	}
}
