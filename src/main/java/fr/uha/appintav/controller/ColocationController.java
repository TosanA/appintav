package fr.uha.appintav.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.error.RecordAlreadyExistsException;
import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.Colocation;
import fr.uha.appintav.model.User;
import fr.uha.appintav.repo.ColocationRepository;
import fr.uha.appintav.repo.UserRepository;

@RestController
@RequestMapping("/colocations")
public class ColocationController {

	@Autowired
	private ColocationRepository colocationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody Integer add() {
		return this.colocationRepository.save(new Colocation()).getId();
	}
	
	@PostMapping(path="/addUser")
	public @ResponseBody Colocation addUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		Optional<User> user = this.userRepository.findById(userId);
		if (user.equals(Optional.empty()))
			throw new RecordNotFoundException("User with id '" + userId + "' does not exist.");
		
		Optional<Colocation> coloc = this.colocationRepository.findById(id);
		if (coloc.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		
		if (coloc.get().getUsers().contains(user.get()))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' already has user with id '" + userId + "'.");
		
		coloc.get().getUsers().add(user.get());
		return this.colocationRepository.save(coloc.get());
	}
	
	@DeleteMapping(path="/removeUser")
	public @ResponseBody Colocation removeUser(
			@RequestParam(required = true) Integer id,
			@RequestParam(required = true) Integer userId) {
		Optional<User> user = this.userRepository.findById(userId);
		if (user.equals(Optional.empty()))
			throw new RecordNotFoundException("User with id '" + userId + "' does not exist.");
		
		Optional<Colocation> coloc = this.colocationRepository.findById(id);
		if (coloc.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		
		if (!coloc.get().getUsers().contains(user.get()))
			throw new RecordAlreadyExistsException("Colocation with id '" + id + "' doesn't has user with id '" + userId + "'.");
		
		coloc.get().getUsers().remove(user.get());
		return this.colocationRepository.save(coloc.get());
	}
	
	@PostMapping(path="/update")
	public @ResponseBody Colocation update(@RequestBody Colocation colocation) {
		return this.colocationRepository.save(colocation);
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam(value = "id", required = true) Integer id) {
		if (this.colocationRepository.findById(id).equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		
		this.colocationRepository.deleteById(id);
		return "Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Colocation> getAllTasks() {
		return this.colocationRepository.findAll();
	}
	
}

