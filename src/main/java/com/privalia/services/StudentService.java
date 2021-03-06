package com.privalia.services;

import com.privalia.domain.Student;

public interface StudentService {

	Iterable <Student> listAll();
	Student getById(Integer id);
	
	Student saveOrUpdate(Student domainObject);
	
	void delete(Integer id);
	
}
