package de.freerider.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.freerider.model.Customer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = CustomerRepository.class)
class CustomerRepositoryTest {

	@Autowired
	private CrudRepository<Customer,String> customerRepository;

	// two sample customers
	private Customer mats;
	private Customer thomas;


	/**
	 * Set up test, runs before EVERY test execution
	 */
	@BeforeEach
	public void beforeEach() {
		//
		mats = new Customer( "Mats", "Hummels", "mh@weltmeister-2014.dfb.de" );
		thomas = new Customer( "Thomas", "Mueller", "th@weltmeister-2014.dfb.de" );

		customerRepository.deleteAll();		// clear repository
		assertEquals( customerRepository.count(), 0 );
	}

	@Test
	void testCreateNewRepositories() {
//		customerRepository = new CustomerRepository();
	}

	@Test
	void testSaveCustomers() {
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
	}



//	@Test
//	void testFive() {
//
//		Customer max = new Customer("Mustermann", "Max", "max@example.com");
//		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
//		Customer michael = new Customer("Mustermann", "Michael", "michael@example.com");
//		Customer monika = new Customer("Mustermann", "Monika", "monika@example.com");
//		Customer michel = new Customer("Mustermann", "Michel", "michel@example.com");
//
//		repo.save(max);
//		repo.save(margret);
//		repo.save(michael);
//		repo.save(monika);
//		repo.save(michel);
//
//		assertEquals(5, repo.count());
//
//		// try saving using save all
//		LinkedList<Customer> customerLinkedList= new LinkedList<Customer>();
//		customerLinkedList.add(max);
//		repo.saveAll(customerLinkedList);
//		assertEquals(5, repo.count());
//
//
//		// try finding max
//		Optional<Customer> foundMax = repo.findById(max.getId());
//		assertEquals("Max", foundMax.get().getFirstName());
//
//		assertTrue(repo.existsById(max.getId()));
//
//		System.out.println("Sind alle Namen vorhanden?:");
//		repo.findAll().forEach(customer -> System.out.println(customer.getFirstName()));
//
//
//		String[] findAllByIdName = new String[]{max.getId()};
//		Iterable<Customer> findAllByIdIter = repo.findAllById(Arrays.asList(findAllByIdName));
//		System.out.println("Ist hier Max?:");
//		findAllByIdIter.forEach(customer -> System.out.println(customer.getFirstName()));
//
//		// try saving same people again
//		repo.save(max);
//		repo.save(margret);
//		repo.save(michael);
//		repo.save(monika);
//		repo.save(michel);
//
//		assertEquals(5, repo.count());
//
//	}
//
//
//	@Test
//	void deleteById() {
//		Customer max = new Customer("Mustermann", "Max", "max@example.com");
//		repo.save(max);
//		assertEquals(1, repo.count());
//		repo.deleteById(max.getId());
//		assertEquals(0, repo.count());
//	}
//
//	@Test
//	void delete() {
//		Customer max = new Customer("Mustermann", "Max", "max@example.com");
//		repo.save(max);
//		assertEquals(1, repo.count());
//		repo.delete(max);
//		assertEquals(0, repo.count());
//	}
//
//	@Test
//	void deleteAllById() {
//		Customer max = new Customer("Mustermann", "Max", "max@example.com");
//		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
//		repo.save(margret);
//		repo.save(max);
//		assertEquals(2, repo.count());
//
//		String[] names = new String[]{max.getId(), margret.getId()};
//		repo.deleteAllById(Arrays.asList(names));
//		assertEquals(0, repo.count());
//	}
//
//	@Test
//	void deleteAll() {
//		Customer max = new Customer("Mustermann", "Max", "max@example.com");
//		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
//		repo.save(margret);
//		repo.save(max);
//		assertEquals(2, repo.count());
//
//		repo.deleteAll();
//		assertEquals(0, repo.count());
//	}
}