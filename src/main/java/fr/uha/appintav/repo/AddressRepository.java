package fr.uha.appintav.repo;

import org.springframework.data.repository.CrudRepository;

import fr.uha.appintav.model.Address;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface AddressRepository extends CrudRepository<Address, Integer>{

}
