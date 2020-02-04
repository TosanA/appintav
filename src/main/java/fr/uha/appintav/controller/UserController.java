package fr.uha.appintav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;

@Component
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	public User add(String email, String password, Integer points) {
		return this.userRepository.save(new User(email, password, points));
	}

	public String delete(String email) {
		this.userRepository.deleteById(email);
		return "User '" + email + "' deleted";
	}

	public Iterable<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}
