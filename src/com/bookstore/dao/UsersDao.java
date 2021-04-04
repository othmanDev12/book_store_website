package com.bookstore.dao;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Users;



public class UsersDao implements GenericDao<Users> {
	
	// create a entityMangerfactory Object;
	
	private EntityManager entitymanger;
	
	public UsersDao(EntityManager entityManager) {
		this.entitymanger = entityManager;
	}
	
	public void catalogDaoImp() {
		// create entity manger factory object
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		// create entity manger object;
		entitymanger = entityManagerFactory.createEntityManager();
		
	}

	@Override
	public Users Create(Users entity) {
		// begin the transaction;
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
	public Users Update(Users entity) {
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
	public Users get(Integer entityId) {
		Users entity = entitymanger.find(Users.class, entityId);
		if(entity != null) {
			entitymanger.refresh(entity);
		}
		return entity;
		
	}
	
	public Boolean checkLogin(String email , String password) {
		String sql = "Users.findEmailPass";
		Query query = entitymanger.createNamedQuery(sql);
		
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		@SuppressWarnings("unchecked")
		List<Users> users = query.getResultList();
		
		if(users != null && users.size() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Users delete(Integer entityId) {
		
		entitymanger.getTransaction().begin();
		
			Users entity = entitymanger.find(Users.class, entityId);
			entitymanger.remove(entity);
			entitymanger.getTransaction().commit();
			
			return entity;
		
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> listAll() {
		String sql = "Users.findAll";
		Query query = entitymanger.createNamedQuery(sql);
		
		return query.getResultList();
	}
	
	public Users findByEmail(String email) {
		String sql = "Users.findEmail";
		Query query = entitymanger.createNamedQuery(sql);
		query.setParameter("email", email);
		
		@SuppressWarnings("unchecked")
		List<Users> listUsers = query.getResultList();
		
		if(listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		
		return null;
	}


	@Override
	public long count() {
		String sql = "Users.count";
		Query query = entitymanger.createNamedQuery(sql);
		
		return (long) query.getSingleResult();
	}

}
