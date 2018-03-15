package com.privalia.services;

import com.privalia.domain.Address;

public interface AddressService {

	Iterable <Address> listAll();
	Address getById(Integer id);
	
	Address saveOrUpdate(Address domainObject);
	
	void delete(Integer id);
	
}
