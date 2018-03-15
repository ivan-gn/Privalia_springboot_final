package com.privalia.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.privalia.domain.Student;


@RepositoryRestResource
public interface StudentRepository extends CrudRepository <Student, Integer>{
	
	Student findByStudentId(String studentId);
	
	Student findByName(String name);
	
	@Modifying
	@Transactional
	@Query("INSERT INTO Student t (idStudent, name) VALUES (:name;:id)")
	int insertStudent(@Param("idStudent")int idStudent,@Param("name") String name);
	
	
}
