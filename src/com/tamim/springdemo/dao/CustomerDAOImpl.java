package com.tamim.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamim.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject the Session Factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// Get the current hibernate session.
		Session currentSession = sessionFactory.getCurrentSession();

		// Create query....sorted by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// Execute query
		List<Customer> customers = theQuery.getResultList();

		// Return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// Get the current hibernate session.
		Session currentSession = sessionFactory.getCurrentSession();

		// Save Customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomers(int theId) {
		// Get the current hibernate session.
		Session currentSession = sessionFactory.getCurrentSession();

		// Retrieve data from database using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);

		// Return customer data
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// Get the current hibernate session.
		Session currentSession = sessionFactory.getCurrentSession();

		//Delete object with primary key
	    Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
	    
	    theQuery.setParameter("customerId", theId);
	    
	    theQuery.executeUpdate();
		
		
	}

}
