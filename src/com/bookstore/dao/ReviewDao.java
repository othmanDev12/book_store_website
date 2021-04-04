package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDao implements GenericDao<Review> {
	
	// INSTANCE VARIABLE;
	private EntityManagerFactory entityMangerFactory;
	private EntityManager entityManger;
	
	// CONSTRUCTORS;
	public ReviewDao() {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityMangerFactory.createEntityManager();
	}
	
	public ReviewDao(EntityManager entityManager) {
		this.entityManger = entityManager;
	}

	@Override
	public Review Create(Review entity) {
		// begin the persistence;
		entityManger.getTransaction().begin();
		try {
			entityManger.persist(entity);
			entityManger.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManger.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Review Update(Review entity) {
		// begin the transaction;
		entityManger.getTransaction().begin();
		try {
			entityManger.merge(entity);
			entityManger.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManger.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Review get(Integer entityId) {
		Review entity = entityManger.find(Review.class, entityId);
		return entity;
	}

	@Override
	public Review delete(Integer entityId) {
		// begin the transaction;
		entityManger.getTransaction().begin();
		Review entity = entityManger.find(Review.class, entityId);
		entityManger.remove(entity);
		entityManger.getTransaction().commit();
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> listAll() {
		String sql = "Review.listAll";
		// creation of queries;
		Query query = entityManger.createNamedQuery(sql);
		return query.getResultList();
	}
	
	public Review findReviewByCustomerAndBook(Integer bookId , Integer customerId) {
		String sql = "Review.findByCustomerAndBook";
		// create query;
		Query query = entityManger.createNamedQuery(sql);
		// set parameters from queries;
		query.setParameter("bookId", bookId);
		query.setParameter("customerId", customerId);
		
		@SuppressWarnings("unchecked")
		List<Review> reviews = query.getResultList();
		
		if(reviews != null && reviews.size() > 0)  {
			return reviews.get(0);
		}
		return null;
	}

	@Override
	public long count() {
		String sql = "Review.count";
		Query query = entityManger.createNamedQuery(sql);
		
		return (long) query.getSingleResult();
	}

}
