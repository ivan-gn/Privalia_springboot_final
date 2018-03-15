package com.privalia.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privalia.entity.Student;
import com.privalia.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentRepository getStudentRepository() {
		return studentRepository;
	}


	@Override
	public Iterable<Student> listAll() {
		logger.debug("listAllStudents called");
		return studentRepository.findAll();
	}

	@Override
	public Student getById(Integer id) {
		logger.debug("getById called");
		return studentRepository.findOne(id);
	}

	@Override
	public Student saveOrUpdate(Student student) {
		logger.debug("saveOrUpdate called");
		return studentRepository.save(student);
	}

	@Override
	public void delete(Integer id) {
		logger.debug("delete called");	
		studentRepository.delete(id);
	}	
}
