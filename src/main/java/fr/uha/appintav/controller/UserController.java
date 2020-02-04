package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;

@Component
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	public @ResponseBody User add(
			@RequestParam(required = true) String name,
			@RequestParam(required = true) Integer points) {
		return this.userRepository.save(new User(name, points));
	}

	public @ResponseBody User update(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		this.userRepository.deleteById(id);
		return "Deleted";
	}

	public @ResponseBody Iterable<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}
