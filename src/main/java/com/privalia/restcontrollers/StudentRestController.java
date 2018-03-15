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

import com.privalia.entity.Student;
import com.privalia.services.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/student")
@Api(
		value = "online store",
		description = "Operations pertaining to students in Online Store"
)
public class StudentRestController {

	private StudentService studentService;
	
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
		
	}
	
	@ApiOperation(value = "Search a student with an ID", response = Student.class)
	@RequestMapping(value = "/show/{id}",method = RequestMethod.GET, produces = "application/json")
	public Student showStudent(@PathVariable Integer id) {
		Student student = studentService.getById(id);
		return student;
	}
	
	@ApiOperation(value = "View a list of available students", response = Iterable.class)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),

	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),

	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")

	public Iterable<Student> list() {

		Iterable<Student> studentList = studentService.listAll();

		return studentList;

	}


	@ApiOperation(value = "Add a student")

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")

	public ResponseEntity<String> saveStudent(@Valid @RequestBody Student student) {

		studentService.saveOrUpdate(student);

		return new ResponseEntity<String>("Student saved successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Update a student")

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")

	public ResponseEntity<String> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {

		Student storedStudent = studentService.getById(id);

		storedStudent.setName(student.getName());

		storedStudent.setStudentId(student.getStudentId());

		studentService.saveOrUpdate(storedStudent);

		return new ResponseEntity<String>("Student updated successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Delete a student")

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")

	public ResponseEntity<String> delete(@PathVariable(value = "id", required = true) Integer id) {

		studentService.delete(id);

		return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);


	}

	
}
