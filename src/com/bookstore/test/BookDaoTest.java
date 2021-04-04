package com.bookstore.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDaoTest {
	private static EntityManager entityManager;
	private static EntityManagerFactory entityMangerFactory;
	private static BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityMangerFactory.createEntityManager();

		// create new instance of bookDoa;
		bookDao = new BookDao(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityMangerFactory.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		// create new instance of books;
		Book newBook = new Book();
		// set values to the book object;
		Category category = new Category();
		category.setCategoryId(11);
		category.setName("Java");
		newBook.setCategory(category);
		newBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		newBook.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming ");
		newBook.setDescription(
				"Java 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming. With Java 8's functional features you can now write more concise code in less time, and also automatically benefit from multicore architectures. It's time to dig in!\r\n"
						+ "\r\n"
						+ "Purchase of the print book includes a free eBook in PDF, Kindle, and ePub formats from Manning Publications.\r\n"
						+ "\r\n" + "About the Book\r\n" + "\r\n"
						+ "Every new version of Java is important, but Java 8 is a game changer. Java 8 in Action is a clearly written guide to the new features of Java 8. It begins with a practical introduction to lambdas, using real-world Java code. Next, it covers the new Streams API and shows how you can use it to make collection-based code radically easier to understand and maintain. It also explains other major Java 8 features including default methods, Optional, CompletableFuture, and the new Date and Time API.\r\n"
						+ "\r\n" + "This book is written for programmers familiar with Java and basic OO programming.");
		newBook.setIsbn("1617291994");
		newBook.setLastUpdate(new Date());
		newBook.setPrice(36.72f);

		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sd.parse("08/28/2014");
		newBook.setPublishDate(date);

		// set image;
		String imagePath = "C:\\Users\\OTHMANE\\Desktop\\bookStoreProject\\BookStoreWebsite\\dummy-data\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book book = bookDao.Create(newBook);

		// assert for testing with condition;
		assertTrue(book != null && book.getBookId() > 0);

	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		// create new instance of books;
		Book exsistBook = new Book();
		// set values to the book object;
		exsistBook.setBookId(36);
		Category category = new Category();
		category.setCategoryId(11);
		category.setName("Java");
		exsistBook.setCategory(category);
		exsistBook.setAuthor("Kathy Sierra, Bert Bates");
		exsistBook.setTitle("Head First Java, 2nd Edition");
		exsistBook.setDescription(
				"Learning a complex new language is no easy task especially when it s an object-oriented computer programming language like Java. You might think the problem is your brain. It seems to have a mind of its own, a mind that doesn't always want to take in the dry, technical stuff you're forced to study.\r\n"
						+ "\r\n"
						+ "The fact is your brain craves novelty. It's constantly searching, scanning, waiting for something unusual to happen. After all, that's the way it was built to help you stay alive. It takes all the routine, ordinary, dull stuff and filters it to the background so it won't interfere with your brain's real work--recording things that matter. How does your brain know what matters? It's like the creators of the Head First approach say, suppose you're out for a hike and a tiger jumps in front of you, what happens in your brain? Neurons fire. Emotions crank up. Chemicals surge. That's how your brain knows.\r\n"
						+ "\r\n"
						+ "And that's how your brain will learn Java. Head First Java combines puzzles, strong visuals, mysteries, and soul-searching interviews with famous Java objects to engage you in many different ways. It's fast, it's fun, and it's effective. And, despite its playful appearance, Head First Java is serious stuff: a complete introduction to object-oriented programming and Java. You'll learn everything from the fundamentals to advanced topics, including threads, network sockets, and distributed programming with RMI. And the new. second edition focuses on Java 5.0, the latest version of the Java language and development platform. Because Java 5.0 is a major update to the platform, with deep, code-level changes, even more careful study and implementation is required. So learning the Head First way is more important than ever.\r\n"
						+ "\r\n"
						+ "If you've read a Head First book, you know what to expect--a visually rich format designed for the way your brain works. If you haven't, you're in for a treat. You'll see why people say it's unlike any other Java book you've ever read.\r\n"
						+ "\r\n"
						+ "By exploiting how your brain works, Head First Java compresses the time it takes to learn and retain--complex information. Its unique approach not only shows you what you need to know about Java syntax, it teaches you to think like a Java programmer. If you want to be bored, buy some other book. But if you want to understand Java, this book's for you.\r\n"
						+ ".");
		exsistBook.setIsbn("0596009208");
		exsistBook.setPrice(27.99f);
		exsistBook.setLastUpdate(new Date());

		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sd.parse("10/09/2006");
		exsistBook.setPublishDate(date);

		// set image;
		String imagePath = "C:\\Users\\OTHMANE\\Desktop\\bookStoreProject\\BookStoreWebsite\\dummy-data\\Head First Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		exsistBook.setImage(imageBytes);

		Book updateBook = bookDao.Update(exsistBook);
		assertEquals(exsistBook.getCategory().getCategoryId(), updateBook.getCategory().getCategoryId());
	}

	@Test
	public void testGetBook() {
		Integer id = 33;
		Book getBook = bookDao.get(id);

		assertNotNull(getBook);
	}

	@Test
	public void testDeleteBook() {
		Integer deleteId = 44;
		Book deleteBook = bookDao.delete(deleteId);

		assertNotNull(deleteBook);
	}

	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();

		assertTrue(listBooks != null);
	}

	@Test
	public void testCount() {
		long count = bookDao.count();
		assertTrue(count > 0);
	}

	@Test
	public void testFindByTitle() {
		String title = "Java 11 in Action: Lambdas, Streams, and functional-style programming";
		Book bookTitle = bookDao.findByTitle(title);

		assertTrue(bookTitle != null);
	}

	@Test
	public void testListByCategory() {
		Integer integer = 11;

		List<Book> listBooks = bookDao.listByCategory(integer);

		assertTrue(listBooks.size() > 0);
	}

	@Test
	public void testListNewBook() {
		List<Book> listBooks = bookDao.listNewBook();
		assertTrue(listBooks.size() > 0);
	}

	@Test
	public void testSearchBookByTitle() {
		String keyword = "Java";
		List<Book> searchListBook = bookDao.searchBooks(keyword);
		
		for(Book titleList: searchListBook) {
			System.out.println(titleList.getTitle());
		}
		
		assertEquals(3, searchListBook.size());
	}
	
	@Test
	public void testSearchBookByAuthor() {
		String keyword = "Danny";
		List<Book> searchListBooks = bookDao.searchBooks(keyword);
		
		for(Book authorList: searchListBooks) {
			System.out.println(authorList.getAuthor());
		}
		
		assertEquals(1, searchListBooks.size());
	}
	
	@Test
	public void testSearchBookByDescription() {
		String keyword = "Master Spring basics and core topics";
		List<Book> searchListBooks = bookDao.searchBooks(keyword);
		
		for(Book descriptionList: searchListBooks) {
			System.out.println(descriptionList.getDescription());
		}
		
		assertEquals(1, searchListBooks.size());
	}
}
