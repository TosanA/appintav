package fr.uha.appintav.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.Colocation;
import fr.uha.appintav.repo.ColocationRepository;

@Service
public class ColocationService {

	@Autowired
	private ColocationRepository colocationRepository;
	
	public Colocation findById(Integer id) {
		Optional<Colocation> coloc = this.colocationRepository.findById(id);
		if (coloc.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		
		return coloc.get();
	}
	
	public void deleteById(Integer id) {
		Optional<Colocation> coloc = this.colocationRepository.findById(id);
		if (coloc.equals(Optional.empty()))
			throw new RecordNotFoundException("Colocation with id '" + id + "' does not exist.");
		this.colocationRepository.deleteById(id);
	}
	
	public Colocation save(Colocation coloc) {
		return this.colocationRepository.save(coloc);
	}
	
	public Iterable<Colocation> findAll(){
		return this.colocationRepository.findAll();		
	}
}
