package com.privalia.services;

import com.privalia.entity.Address;

public interface AddressService {

	Iterable <Address> listAll();
	Address getById(Integer id);
	
	Address saveOrUpdate(Address domainObject);
	
	void delete(Integer id);
	
}
