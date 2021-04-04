package com.booksote.controller.learning;

import java.lang.invoke.StringConcatFactory;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Users;



public class JpaDao<E> {
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	
	public JpaDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public JpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public E create(E entity) {
		// begin the transaction;
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}
	
	public E update(E entity) {
		// begin the transaction
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public E find(Class<E> type , Object entityId) {
		E entity = entityManager.find(type, entityId);
		return entity;
	}
	
	public E remove(Class<E> type , Object entityId) {
		E entity = entityManager.find(type, entityId );
		entityManager.remove(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findWithNamedQuery(String sql) {
		Query query = entityManager.createNamedQuery(sql);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<E> findWithNamedQuery(String sql , String parameterKeyword , String parameterValue) {
		Query query = entityManager.createNamedQuery(sql);
		query.setParameter(parameterKeyword, parameterValue);
		return query.getResultList();
	}
	
		
	public long countWithNamedQuery(String sql) {
		Query query = entityManager.createNamedQuery(sql);
		return (long) query.getSingleResult();
	}

}
