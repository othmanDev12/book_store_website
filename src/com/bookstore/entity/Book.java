package com.bookstore.entity;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "book")
@NamedQueries({
	@NamedQuery(name = "Book.listAll" , query = "SELECT b FROM Book b GROUP BY b.title"),
	@NamedQuery(name = "Book.count" , query = "SELECT count(*) FROM Book b"),
	@NamedQuery(name = "Book.findByTitle" , query = "SELECT b FROM Book b  WHERE b.title = :title"),
	@NamedQuery(name = "Book.findByCategory" , query = "SELECT b FROM Book b JOIN Category c ON b.category.categoryId = "
			+ "c.categoryId AND c.categoryId = :catId"),
	@NamedQuery(name = "Book.listNew" , query = "SELECT b FROM Book b ORDER BY b.publishDate DESC"),
	@NamedQuery(name = "Book.search" , query = "SELECT b FROM Book b WHERE b.title LIKE '%' || :keyword || '%' OR b.author LIKE '%'"
			+ " || :keyword || '%' OR b.description LIKE '%' || :keyword || '%'")
})
public class Book {
	// INSTANCE VARIABLE;
	private Integer bookId;
	private String title;
	private String author;
	private String description;
	private String isbn;
	private byte[] image;
	private String base64Image;
	private Float price;
	private Date publishDate;
	private Date lastUpdate;
	private Category category;
	private String ratingStars;
	
	private Set<Review> reviews = new HashSet<Review>();
	

	public Book() {

	}

	public Book(String title, String author, String description, String isbn, byte[] image, Float price,
			Date publishDate, Date lastUpdate, Category category) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdate = lastUpdate;
		this.category = category;
	}
	
	

	public Book(String title, String author, String description, String isbn, byte[] image, 
			Float price, Date publishDate) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] imageBytes) {
		this.image = imageBytes;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "publish_date")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "last_update_time")
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
	@ManyToOne
	@JoinColumn(name = "category_id" , nullable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	@Transient
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(this.image);
	}
	
	@Transient
	public void setBase64Image(String base64Image) {
		this.base64Image =  base64Image;
	}
	
	
	@Transient
	public String getRatingStars() {
		float avergeRating = calculAvergeRating();
		
		return ratingString(avergeRating);
	}
	
	@Transient
	public void setRatingStars(String ratingStars) {
		this.ratingStars = ratingStars;
	}


	@Transient
	public float calculAvergeRating() {
		float avregeRating = 0.0f;
		int sum = 0;
		//check if the book has no rating;
		if(reviews.isEmpty()) {
			return 0.0f;
		}
		
		
		for(Review review: reviews) {
			sum += review.getRating();
		}
		
		avregeRating = (float) sum / reviews.size();
		
		return avregeRating;
	}
	
	@Transient
	public String ratingString(float avregeRating) {
		String resualt = "";
		int starsRating = (int) avregeRating;
		
		for(int i = 1 ; i <= starsRating ; i++) {
			resualt +=  "on,";
		}
		
		int next = starsRating + 1;
		
		if(avregeRating > starsRating) {
			resualt +=  "half,";
			next++;
		}
		
		for(int i = next ; i <= 5 ; i++) {
			resualt += "off,";
		}
		
		return resualt.substring(0 , resualt.length() - 1);
	}
	
	
	
}
