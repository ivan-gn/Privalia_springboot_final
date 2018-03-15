package com.privalia.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.privalia.entity.Address;
import com.privalia.services.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/address")
@Api(
		value = "online store",
		description = "Operations pertaining to addresses in Online Store"
)
public class AddressRestController {

	private AddressService addressService;
	
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
		
	}
	
	@ApiOperation(value = "Search a address with an ID", response = Address.class)
	@RequestMapping(value = "/show/{id}",method = RequestMethod.GET, produces = "application/json")
	public Address showAddress(@PathVariable Integer id) {
		Address address = addressService.getById(id);
		return address;
	}
	
	@ApiOperation(value = "View a list of available addresss", response = Iterable.class)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),

	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),

	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")

	public Iterable<Address> list() {

		Iterable<Address> addressList = addressService.listAll();

		return addressList;

	}


	@ApiOperation(value = "Add a address")

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")

	public ResponseEntity<String> saveAddress(@Valid @RequestBody Address address) {

		addressService.saveOrUpdate(address);

		return new ResponseEntity<String>("Address saved successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Update a address")

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")

	public ResponseEntity<String> updateAddress(@PathVariable Integer id, @Valid @RequestBody Address address) {

		Address storedAddress = addressService.getById(id);

		storedAddress.setStreet(address.getStreet());

		storedAddress.setStreet(address.getStreet());

		storedAddress.setZipCode(address.getZipCode());

		addressService.saveOrUpdate(storedAddress);

		return new ResponseEntity<String>("Address updated successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Delete a address")

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")

	public ResponseEntity<String> delete(@PathVariable(value = "id", required = true) Integer id) {

		addressService.delete(id);

		return new ResponseEntity<String>("Address deleted successfully", HttpStatus.OK);


	}

	
}
