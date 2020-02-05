package fr.uha.appintav.routes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.controller.AddressController;
import fr.uha.appintav.model.Address;
import fr.uha.appintav.model.Colocation;

@RestController
@RequestMapping("/addresses")
public class AddressRoutes {
	
	@Autowired
	private AddressController addressController;
	
	@PostMapping(path="/add")
	public @ResponseBody Address add(
			@RequestParam(required = true) Integer number,
			@RequestParam(required = true) String street,
			@RequestParam(required = true) Integer postalCode,
			@RequestParam(required = true) String city){
		return this.addressController.add(number, street, postalCode, city);	
	}
	
	@PostMapping(path="/addColocation")
	public @ResponseBody Address addColocation(
			@RequestParam(required = true) Integer addressId,
			@RequestParam(required = true) Integer colocationId){
		return this.addressController.addColocation(addressId, colocationId);	
	}
	
	@GetMapping(path="/city")
	public @ResponseBody Iterable<Address> getAddressesByCity(
			@RequestParam(required = true) String city){
		return this.addressController.getAdressByCity(city);
	}
	
	@GetMapping(path="/id")
	public @ResponseBody Address getAddressesById(
			@RequestParam(required = true) Integer id){
		return this.addressController.getAdressById(id);
	}
	
	@GetMapping(path="/colocations")
	public @ResponseBody Iterable<Colocation> getColocations(
			@RequestParam(required = true) Integer id){
		return this.addressController.getColocations(id);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Address> getAll(){
		return this.addressController.getAllAddresses();
	}
	

}
