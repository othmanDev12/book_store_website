package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Category;


public class CategoryDao implements GenericDao<Category> {
	// instance 
	private EntityManager entitymanger;
	private EntityManagerFactory entityMangerFactory;
	
	
	// CONSTRUCTORS;
	public CategoryDao(EntityManager entitymanger) {
		this.entitymanger = entitymanger;
	}
    public CategoryDao() {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entitymanger = entityMangerFactory.createEntityManager();
	}

	@Override
	public Category Create(Category entity) {
	    entitymanger.getTransaction().begin();
	    try {
			entitymanger.persist(entity);
			entitymanger.getTransaction().commit();
		} catch (Exception e) {
			entitymanger.getTransaction().rollback();
			e.printStackTrace();
		}
	    
	    return entity;
	}

	@Override
	public Category Update(Category entity) {
		entitymanger.getTransaction().begin();
		try {
			entitymanger.merge(entity);
			entitymanger.getTransaction().commit();
		} catch (Exception e) {
			entitymanger.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Category get(Integer entityId) {
		Category entity = entitymanger.find(Category.class, entityId);
		return entity;
	}

	@Override
	public Category delete(Integer entityId) {
		entitymanger.getTransaction().begin();
		Category entity = entitymanger.find(Category.class, entityId);
		entitymanger.remove(entity);
		entitymanger.getTransaction().commit();
		return entity;
	} 

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listAll() {
		String sql = "Category.findAll";
		Query query = entitymanger.createNamedQuery(sql);
		
		return query.getResultList();
	}
	
	public Category findByName(String name) {
		String sql = "Category.findName";
		Query query = entitymanger.createNamedQuery(sql);
		query.setParameter("name", name);
		
		@SuppressWarnings("unchecked")
		List<Category> categories = query.getResultList();
		if(categories != null && categories.size() > 0) {
			return categories.get(0);
		}
		return null;
	}

	@Override
	public long count() {
		String sql = "Category.count";
		Query query = entitymanger.createNamedQuery(sql);
		
		return (long) query.getSingleResult();
	}

}
