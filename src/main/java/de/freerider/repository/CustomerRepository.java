package de.freerider.repository;

import de.freerider.model.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;


@Component
class CustomerRepository implements CrudRepository<Customer, String> {
	//
	private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );
	private final HashMap<String, Customer> customers = new HashMap<String, Customer>();

	@Override
	public <S extends Customer> S save(S entity) {
		// if customer does not have an ID
		if(entity.getId() == null || entity.getId().length() == 0) {
			String newId = idGen.nextId();
			// generate a new ID as long as it's not already taken
			while(customers.get(newId) != null) {
				newId = idGen.nextId();
			}
			entity.setId(idGen.nextId());
		}
		customers.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		entities.forEach(this::save);
		return entities;
	}

	@Override
	public Optional<Customer> findById(String s) {
		Customer customer = customers.get(s);
		if(customer == null) {
			return Optional.empty();
		} else {
			return Optional.of(customer);
		}
	}

	@Override
	public boolean existsById(String s) {
		return customers.get(s) != null;
	}

	@Override
	public Iterable<Customer> findAll() {
		return customers.values();
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<String> strings) {
		LinkedList<Customer> filteredCustomers = new LinkedList<Customer>();

		strings.forEach(string -> {
			Customer foundCustomer = customers.get(string);
			if(foundCustomer != null) {
				filteredCustomers.add(foundCustomer);
			}
		});

		return filteredCustomers;
	}

	@Override
	public long count() {
		return customers.size();
	}

	@Override
	public void deleteById(String s) {
		customers.remove(s);
	}

	@Override
	public void delete(Customer entity) {
		if(entity == null) {
			throw new IllegalArgumentException();
		}
		customers.remove(entity.getId());
	}

	@Override
	public void deleteAllById(Iterable<? extends String> strings) {
		strings.forEach(this::deleteById);
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		entities.forEach(this::delete);
	}

	@Override
	public void deleteAll() {
		customers.clear();
	}
}