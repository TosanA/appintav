package fr.uha.appintav.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import fr.uha.appintav.controller.UserController;
import fr.uha.appintav.model.User;

@RestController
@RequestMapping("/users")
public class UserRoutes {

	@Autowired
	private UserController userController;
	
	@PostMapping(path="/add")
	public @ResponseBody User add(
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String password,
			@RequestParam(required = true) Integer points) {
		return this.userController.add(email, password, points);
	}
		
	@DeleteMapping(path="/delete")
	public @ResponseBody ResponseEntity<String> delete(
			@RequestParam(required = true) String email) {
		return this.userController.delete(email);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return this.userController.getAllUsers();
	}
}
