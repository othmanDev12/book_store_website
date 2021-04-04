package com.bookstore.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.bookstore.entity.Customer;

public class CustomerDao implements GenericDao<Customer> {
	// INSTANCE VARIABLES;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	// CONSTRUCTOR;
	public CustomerDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public CustomerDao(EntityManager entityManager) {
	   this.entityManager = entityManager;
	}

	@Override
	public Customer Create(Customer entity) {
		// begin the persistence;
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Customer Update(Customer entity) {
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Customer get(Integer entityId) {
		Customer entity = entityManager.find(Customer.class, entityId);
		return entity;
	}

	@Override
	public Customer delete(Integer entityId) {
		entityManager.getTransaction().begin();
		Customer entity = entityManager.find(Customer.class, entityId);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listAll() {
		String sql = "Customer.listAll";
		Query query = entityManager.createNamedQuery(sql);
		
		return query.getResultList();
	}

	@Override
	public long count() {
		String sql = "Customer.count";
		Query query = entityManager.createNamedQuery(sql);
		
		return (long) query.getSingleResult();
	}
	
	public Customer findCustomerByEmail(String email) {
		String sql = "Customer.findByEmail";
		Query query = entityManager.createNamedQuery(sql);
		query.setParameter("email", email);
		
		@SuppressWarnings("unchecked")
		List<Customer> listCustomers = query.getResultList();
		if(listCustomers != null && listCustomers.size() > 0) {
			// we take in this case just first element;
			return listCustomers.get(0);
		}
		return null;
	}
	
	public Customer checkLogin(String email , String password) {
		String sql = "Customer.findEmailPass";
		Query query = entityManager.createNamedQuery(sql);
		
		query.setParameter("email", email);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.getResultList();
		
		if(customers !=  null && customers.size() == 1) {
			return customers.get(0);
		}
		
		return null;
	}

  
}
