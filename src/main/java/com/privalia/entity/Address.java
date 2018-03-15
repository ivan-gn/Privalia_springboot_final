package com.privalia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate(value = true)
public class Address {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database address ID")
	private Integer id;
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The address street")
	@Size(min = 4 , max = 50)
	private String street;
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The address zipcode")
	@Size(min = 4 , max = 100)
	private String zipCode;
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The address city")
	@Size(min = 3 , max = 50)
	private String city;
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The address address id")
	@Size(min = 4 , max = 50)
	private String addressId;
	
}
