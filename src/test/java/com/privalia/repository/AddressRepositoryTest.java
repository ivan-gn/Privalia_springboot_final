package com.privalia.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import com.privalia.domain.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class})
public class AddressRepositoryTest {

	private AddressRepository studentRepository;
	private Address student1  = null;
	private Address student2 = null;
	@Rule
	public TestName testName = new TestName();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressRepositoryTest.class);
	
	@Autowired
	public void setAddressRepository(AddressRepository studentRepository) {
		this.studentRepository = studentRepository; 
	}
	
	@Before
	public void setUp() throws Exception{
		
		student1 = new Address();
		student1.setStreet("The machine");
		student1.setAddressId("1232");
		studentRepository.save(student1);
		
		student2 = new Address();
		student2.setStreet("Pepe palote");
		student2.setAddressId("986");
		studentRepository.save(student2);
		
		LOGGER.info("Started test "+ testName.getMethodName());

	}
	
	
	@After
	public void after() throws Exception{
		studentRepository.deleteAll();
		LOGGER.info("Finished test "+ testName.getMethodName());

	}
	
	@Test
	public void testSaveAddress() {
		Address student = new Address();
		student.setStreet("Description cambiada");
		student.setAddressId("1234");
		
		assertNull(student.getId());
		
		studentRepository.save(student);
		
		assertNotNull(student.getId());

		
	}
	
	@Test
	public void testGetAllAddresss() {
		Iterable<Address> students = studentRepository.findAll();
		long size = students.spliterator().getExactSizeIfKnown();
		assertEquals(2,size);
	}
	
	@Test
	public void testModifyAddress() {
		student1.setStreet("Name cambiado");
		Address updatedAddress = studentRepository.save(student1);
		assertEquals(updatedAddress.getStreet(), "Description cambiado");

	}

	@Test
	public void testDelete() {
		studentRepository.delete(student2.getId());
		assertNull(studentRepository.findOne(student2.getId()));
	}
	
	@Test
	public void testFindByAddressId() {
		assertNotNull(studentRepository.findByAddressId(student1.getAddressId()));
	}
	
	@Test
	public void  testfindByDescriptionAndPrice() {
		assertNotNull(studentRepository.findByStreet(student1.getStreet()));
	}
	
	@Test
	public void testInsert(){
		assertTrue(studentRepository.insertAddress(student1.getId(), "UPDATED DESCRIPTION") == 1);
	}
		
}
