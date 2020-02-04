package fr.uha.appintav.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;
import fr.uha.appintav.utils.JwsUtils;


@Component
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	public String login(String email, String password) {
		User user = null;
		try {
			user = this.userRepository.findById(email).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User with id '" + email + "' does not exist.");
		}
		
		if (!user.getPassword().equals(password/*Jwts.builder().setSubject(password).signWith(key).compact()*/)) {
			throw new RecordNotFoundException("Password don't match.");
		}
		
		return JwsUtils.getInstance().saveToken(email);
	}
	
	public ResponseEntity<String> logout(String token) {
		// TODO
		/*try {
		    //Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		} catch (JwtException e) {
			return new ResponseEntity<>("This token doesn't fit with anyone.", HttpStatus.GATEWAY_TIMEOUT);
		}*/
		return new ResponseEntity<>("Logout succed.", HttpStatus.OK);
	}
	
}
