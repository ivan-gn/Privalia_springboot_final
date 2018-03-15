package com.privalia.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.privalia.entity.Address;


@RepositoryRestResource
public interface AddressRepository extends CrudRepository <Address, Integer>{
	Address findByAddressId(String addressId);
	
	Address findByStreet(String street);

	@Modifying
	@Transactional
	@Query("INSERT INTO Address p (idAddress, street) VALUES (:idAddress;:street)")
	int insertAddress(@Param("idAddress")int idAddress,@Param("street") String street);
}
