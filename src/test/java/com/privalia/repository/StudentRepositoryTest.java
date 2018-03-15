package com.privalia.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.privalia.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class})
public class StudentRepositoryTest {

	private StudentRepository studentRepository;
	private Student student1  = null;
	private Student student2 = null;
	@Rule
	public TestName testName = new TestName();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTest.class);
	
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository; 
	}
	
	@Before
	public void setUp() throws Exception{
		
		student1 = new Student();
		student1.setName("The machine");
		student1.setStudentId("1232");
		studentRepository.save(student1);
		
		student2 = new Student();
		student2.setName("Pepe palote");
		student2.setStudentId("986");
		studentRepository.save(student2);
		
		LOGGER.info("Started test "+ testName.getMethodName());

	}
	
	
	@After
	public void after() throws Exception{
		studentRepository.deleteAll();
		LOGGER.info("Finished test "+ testName.getMethodName());

	}
	
	@Test
	public void testSaveStudent() {
		Student student = new Student();
		student.setName("Description cambiada");
		student.setStudentId("1234");
		
		assertNull(student.getId());
		
		studentRepository.save(student);
		
		assertNotNull(student.getId());

		
	}
	
	@Test
	public void testGetAllStudents() {
		Iterable<Student> students = studentRepository.findAll();
		long size = students.spliterator().getExactSizeIfKnown();
		assertEquals(2,size);
	}
	
	@Test
	public void testModifyStudent() {
		student1.setName("Name cambiado");
		Student updatedStudent = studentRepository.save(student1);
		assertEquals(updatedStudent.getName(), "Description cambiado");

	}

	@Test
	public void testDelete() {
		studentRepository.delete(student2.getId());
		assertNull(studentRepository.findOne(student2.getId()));
	}
	
	@Test
	public void testFindByStudentId() {
		assertNotNull(studentRepository.findByStudentId(student1.getStudentId()));
	}
	
	@Test
	public void  testfindByDescriptionAndPrice() {
		assertNotNull(studentRepository.findByName(student1.getName()));
	}
	
	@Test
	public void testInsert(){
		assertTrue(studentRepository.insertStudent(student1.getId(), "UPDATED DESCRIPTION") == 1);
	}
		
}
