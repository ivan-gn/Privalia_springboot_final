package com.privalia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate(value = true)
public class Student {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database student ID")
	private Integer id;
	
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The student name")
	@Size(min = 6 , max = 50)
	private String name;
	
	@Getter
	@Setter
	@ApiModelProperty(notes = "The application specific student ID")
	private String studentId;
	
	@Autowired
	@Qualifier("address")
	@Getter
	@Setter
	@ApiModelProperty(notes = "The student related address")
	private Address address;
	
	
	
}
