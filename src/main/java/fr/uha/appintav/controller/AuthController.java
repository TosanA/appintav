package fr.uha.appintav.controller;

import java.security.Key;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.UserRepository;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthController {

	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
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
			System.out.println(user.getPassword());
			System.out.println(Jwts.builder().setSubject(password).signWith(key).compact());
			throw new RecordNotFoundException("Password don't match.");
		}
		Date date = new Date();
		date.setMinutes(date.getMinutes() + 30);
		return Jwts.builder().setSubject(email).setExpiration(date).signWith(key).compact();
	}
	
	public String logout(String token) {
		try {
		    Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		} catch (JwtException e) {
			return "This token doesn't fit with anyone.";
		}
		return "Logout succed.";
	}
	
}
