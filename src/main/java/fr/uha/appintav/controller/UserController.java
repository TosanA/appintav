package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody User add(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@PostMapping(path="/update")
	public @ResponseBody User update(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@PostMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		this.userRepository.deleteById(id);
		return "Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
}
