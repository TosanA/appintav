package fr.uha.appintav.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.uha.appintav.error.RecordAlreadyExistsException;
import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.Address;
import fr.uha.appintav.model.Colocation;
import fr.uha.appintav.repo.AddressRepository;
import fr.uha.appintav.repo.ColocationRepository;

@Component
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ColocationRepository colocationRepository;


	public Address add(Integer number, String street, Integer postalCode, String city) {
		return this.addressRepository.save(new Address(number, street, postalCode, city));
	}
	
	public Address addColocation(Integer addressId, Integer colocationId) {
		Address address = null;
		Colocation colocation = null;
		try {
			address = this.addressRepository.findById(addressId).get();
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Address with id '" + addressId + "' does not exist.");
		}
		try {
			colocation = this.colocationRepository.findById(colocationId).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Colocation with id '" + colocationId + "' does not exist.");
		}
		if(address.getColocation().contains(colocation))
			throw new RecordAlreadyExistsException("Address with id '" + addressId + "' already has colocation with id '" + colocationId + "'.");
		
		colocation.setAddress(address);
		this.colocationRepository.save(colocation);
		address.getColocation().add(colocation);
		return address;	
	}
	
	public Iterable<Address> getAdressByCity(String city){
		List<Address> addresses = new ArrayList<Address>();
		for(Address address : this.addressRepository.findAll()) {
			if(address.getCity().equals(city)) {
				addresses.add(address);
			}
		}
		Iterable<Address> addressesToReturn = addresses;
		return addressesToReturn;
	}
	
	public Address getAdressById(Integer id){
		Address address = null;
		try {
			address = this.addressRepository.findById(id).get();			
		}catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Address with id '" + id + "' does not exist.");
		}
		return address;
	}
	
	public Iterable<Colocation> getColocations(Integer id){
		List<Colocation> colocations = new ArrayList<Colocation>();
		for(Address address : this.addressRepository.findAll()) {
			if(address.getId() == id) {
				colocations = address.getColocation();
			}
		}
		return colocations;
	}

	public Iterable<Address> getAllAddresses() {
		return this.addressRepository.findAll();
	}

}
