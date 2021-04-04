package com.bookstore.dao;

import java.util.List;

public interface GenericDao<E> {
	
	public E Create(E entity);
	
	public E Update(E entity);
	
	public E get(Integer entityId);
	
	public E delete(Integer entityId);
	
	public List<E> listAll();
	
	public long count();
}
