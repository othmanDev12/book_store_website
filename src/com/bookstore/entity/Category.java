package com.bookstore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@NamedQueries({
	@NamedQuery(name = "Category.findAll" , query = "SELECT c FROM Category c GROUP BY c.name"),
	@NamedQuery(name = "Category.findName" , query = "SELECT c FROM Category c WHERE c.name = :name"),
	@NamedQuery(name = "Category.count" , query = "SELECT COUNT(c) FROM Category c")
})
public class Category {
	// INSTANCE VARIABLE;
	private Integer categoryId;
	private String name;

	private Set<Book> books;

	// CONSTRUCTORS;
	public Category() {

	}
	
	

	public Category(Integer categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}


	public Category(String name) {
		this.name = name;
	}
	

	// GETTERS AND SETTERS;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
