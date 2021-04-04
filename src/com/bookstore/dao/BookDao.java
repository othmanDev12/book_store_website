package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



import com.bookstore.entity.Book;

public class BookDao implements GenericDao<Book> {
	private EntityManager entityManger;
	private EntityManagerFactory entityMangerFactory;

	// CONSTRUCTORS;
	public BookDao() {
	    entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	    entityManger = entityMangerFactory.createEntityManager();

	}
	
	public BookDao(EntityManager entityManager) {
		this.entityManger = entityManager;
	}

	@Override
	public Book Create(Book entity) {

		// begin the transaction;
		
		entityManger.getTransaction().begin();
		try {
			entityManger.persist(entity);
			entityManger.getTransaction().commit();
		} catch (Exception e) {
			entityManger.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Book Update(Book entity) {
		entityManger.getTransaction().begin();
		try {
			entityManger.merge(entity);
			entityManger.getTransaction().commit();
		} catch (Exception e) {
			entityManger.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Book get(Integer entityId) {
		Book getBook = entityManger.find(Book.class, entityId);
		return getBook;
	}

	@Override
	public Book delete(Integer entityId) {
		entityManger.getTransaction().begin();
		Book deleteBook = entityManger.find(Book.class, entityId);
		entityManger.remove(deleteBook);
		entityManger.getTransaction().commit();
		return deleteBook;
	}

	@Override
	public List<Book> listAll() {
		String sql = "Book.listAll";
		// create a query;
		Query query = entityManger.createNamedQuery(sql);
		@SuppressWarnings("unchecked")
		List<Book> books = query.getResultList();
		return books;
	}

	@Override
	public long count() {
		String sql = "Book.count";
		Query query = entityManger.createNamedQuery(sql);
		
		return (long) query.getSingleResult();
	}
	
	public Book findByTitle(String title) {
		String sql = "Book.findByTitle";
		Query query = entityManger.createNamedQuery(sql);
		
		query.setParameter("title", title);
		@SuppressWarnings("unchecked")
		List<Book> listBooks = query.getResultList();
		if(listBooks != null && listBooks.size() > 0) {
			return listBooks.get(0);
		}
		else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listByCategory(Integer categoryId) {
		String sql = "Book.findByCategory";
		Query query = entityManger.createNamedQuery(sql);
		query.setParameter("catId", categoryId);
		return query.getResultList();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Book> listNewBook() {
		String sql = "Book.listNew";
		Query query = entityManger.createNamedQuery(sql);
		// limit bound selected for list book;
		query.setFirstResult(0);
		query.setMaxResults(4);
		
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> searchBooks(String keyword) {
		String sql = "Book.search";
		Query query = entityManger.createNamedQuery(sql);
		// set the parameter to be the keyword;
		query.setParameter("keyword", keyword);
	
		return query.getResultList();
	}

}
