package com.booksote.controller.learning;


import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.controller.frontend.SearchBookServlet;
import com.bookstore.dao.GenericDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Users;

public class ProductDao extends JpaDao<Users> implements GenericDao<Users> {
	
    // Constructor
	public ProductDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Users Create(Users entity) {
		return super.create(entity);
	}

	@Override
	public Users Update(Users entity) {
		return super.update(entity);
	}

	@Override
	public Users get(Integer entityId) {
		return super.find(Users.class, entityId);
	}

	@Override
	public Users delete(Integer entityId) {
		return super.remove(Users.class, entityId);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.listAll");
	}
	
	public Users findUsersByEmail(String email) {
	  List<Users> listUesrs = super.findWithNamedQuery("Users.findByEmail", "email", email);
	  
	  if(listUesrs != null && listUesrs.size() > 0) {
		  return listUesrs.get(0);
	  }
	  else {
		  return null;
	  }
	}
	
	public List<Users> SearchBooks(String keyword) {
		return super.findWithNamedQuery("Users.search", "keyword", keyword);
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.count");
	}
	
}
