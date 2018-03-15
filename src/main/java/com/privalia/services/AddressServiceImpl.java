package com.privalia.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privalia.entity.Address;
import com.privalia.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AddressRepository addressRepository;
	
	@Autowired
	public AddressRepository getAddressRepository() {
		return addressRepository;
	}


	@Override
	public Iterable<Address> listAll() {
		logger.debug("listAllAddresss called");
		return addressRepository.findAll();
	}

	@Override
	public Address getById(Integer id) {
		logger.debug("getById called");
		return addressRepository.findOne(id);
	}

	@Override
	public Address saveOrUpdate(Address address) {
		logger.debug("saveOrUpdate called");
		return addressRepository.save(address);
	}

	@Override
	public void delete(Integer id) {
		logger.debug("delete called");	
		addressRepository.delete(id);
	}	
}
