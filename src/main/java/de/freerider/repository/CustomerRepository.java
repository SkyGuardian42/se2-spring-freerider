package de.freerider.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.freerider.model.Customer;


/**
 * Customer-Repository implementation of the CrudRepository<Customer, String>
 * interface using an internal HashMap<String, Customer>.
 *
 * @Component with @Qualifier to differentiate from other components of the
 * CrudRepository<Customer, String> interface.
 *
 */

@Component
public class CustomerRepository implements CrudRepository<Customer, String> {
	//
	private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );
	private final HashMap<String, Customer> customers = new HashMap<String, Customer>();

	@Override
	public <S extends Customer> S save(S entity) {

		if(entity == null) {
			throw new IllegalArgumentException("ID can't be null");
		}

		// if customer does not have an ID
		if(entity.getId() == null || entity.getId().length() == 0) {
			String newId = idGen.nextId();
			// generate a new ID as long as it's not already taken
			while(customers.get(newId) != null) {
				newId = idGen.nextId();
			}
			entity.setId(idGen.nextId());
		}

//		Customer prevCustomer = customers.get(entity.getId())

		Customer prevCustomer = customers.get(entity.getId());

		customers.put(entity.getId(), entity);

		if(prevCustomer != null) {
			return (S) prevCustomer;
		}

		return entity;
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		if(entities == null) {
			throw new IllegalArgumentException("list of entities can't be null");
		}

		entities.forEach(this::save);
		return entities;
	}

	@Override
	public Optional<Customer> findById(String s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}

		Customer customer = customers.get(s);
		if(customer == null) {
			return Optional.empty();
		} else {
			return Optional.of(customer);
		}
	}

	@Override
	public boolean existsById(String s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}

		return customers.get(s) != null;
	}

	@Override
	public Iterable<Customer> findAll() {
		return customers.values();
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<String> strings) {
		if(strings == null) {
			throw new IllegalArgumentException();
		}

		LinkedList<Customer> filteredCustomers = new LinkedList();



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
		if(s == null) {
			throw new IllegalArgumentException();
		}

		customers.remove(s);
	}

	@Override
	public void delete(Customer entity) {
		if(entity == null) {
			throw new IllegalArgumentException();
		}

		if(entity.getId() == null) {
			throw new IllegalArgumentException("ID of entity can't be null");
		}

		customers.remove(entity.getId());
	}

	@Override
	public void deleteAllById(Iterable<? extends String> strings) {
		if(strings == null) {
			throw new IllegalArgumentException();
		}


		strings.forEach(this::deleteById);
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		if(entities == null) {
			throw new IllegalArgumentException();
		}

		entities.forEach(this::delete);
	}

	@Override
	public void deleteAll() {
		customers.clear();
	}
}