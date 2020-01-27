package fr.uha.appintav.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findById(Integer id) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.equals(Optional.empty()))
			throw new RecordNotFoundException("User with id '" + id + "' does not exist.");
		
		return user.get();
	}
}
