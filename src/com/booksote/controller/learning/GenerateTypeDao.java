package com.booksote.controller.learning;

import java.util.List;

public interface GenerateTypeDao<E> {
   
	public E create(E entity);
	public E update(E entity);
	public E get(Object entityId);
	public E delete(Object entityId);
	public List<E> listAll();
	public long count();
}
