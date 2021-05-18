package de.freerider.repository;

import de.freerider.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
	CustomerRepository repo = new CustomerRepository();

	@Test
	void testFive() {

		Customer max = new Customer("Mustermann", "Max", "max@example.com");
		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
		Customer michael = new Customer("Mustermann", "Michael", "michael@example.com");
		Customer monika = new Customer("Mustermann", "Monika", "monika@example.com");
		Customer michel = new Customer("Mustermann", "Michel", "michel@example.com");

		repo.save(max);
		repo.save(margret);
		repo.save(michael);
		repo.save(monika);
		repo.save(michel);

		assertEquals(5, repo.count());

		// try saving using save all
		LinkedList<Customer> customerLinkedList= new LinkedList<Customer>();
		customerLinkedList.add(max);
		repo.saveAll(customerLinkedList);
		assertEquals(5, repo.count());


		// try finding max
		Optional<Customer> foundMax = repo.findById(max.getId());
		assertEquals("Max", foundMax.get().getFirstName());

		assertTrue(repo.existsById(max.getId()));

		System.out.println("Sind alle Namen vorhanden?:");
		repo.findAll().forEach(customer -> System.out.println(customer.getFirstName()));


		String[] findAllByIdName = new String[]{max.getId()};
		Iterable<Customer> findAllByIdIter = repo.findAllById(Arrays.asList(findAllByIdName));
		System.out.println("Ist hier Max?:");
		findAllByIdIter.forEach(customer -> System.out.println(customer.getFirstName()));

		// try saving same people again
		repo.save(max);
		repo.save(margret);
		repo.save(michael);
		repo.save(monika);
		repo.save(michel);

		assertEquals(5, repo.count());

	}


	@Test
	void deleteById() {
		Customer max = new Customer("Mustermann", "Max", "max@example.com");
		repo.save(max);
		assertEquals(1, repo.count());
		repo.deleteById(max.getId());
		assertEquals(0, repo.count());
	}

	@Test
	void delete() {
		Customer max = new Customer("Mustermann", "Max", "max@example.com");
		repo.save(max);
		assertEquals(1, repo.count());
		repo.delete(max);
		assertEquals(0, repo.count());
	}

	@Test
	void deleteAllById() {
		Customer max = new Customer("Mustermann", "Max", "max@example.com");
		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
		repo.save(margret);
		repo.save(max);
		assertEquals(2, repo.count());

		String[] names = new String[]{max.getId(), margret.getId()};
		repo.deleteAllById(Arrays.asList(names));
		assertEquals(0, repo.count());
	}

	@Test
	void deleteAll() {
		Customer max = new Customer("Mustermann", "Max", "max@example.com");
		Customer margret = new Customer("Mustermann", "Margret", "margret@example.com");
		repo.save(margret);
		repo.save(max);
		assertEquals(2, repo.count());

		repo.deleteAll();
		assertEquals(0, repo.count());
	}
}