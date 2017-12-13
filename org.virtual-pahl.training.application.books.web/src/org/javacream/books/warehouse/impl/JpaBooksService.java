package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BookException.BookExceptionType;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */

@Repository()
public class JpaBooksService implements BooksService {

	@Autowired
	@Qualifier("sequence") // @Qualifier("traced-sequence")
	private IsbnGenerator isbnGenerator;
	@Autowired // @Qualifier("traced")
	private StoreService storeService;
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		entityManager.persist(book);
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Book findBookByIsbn(String isbn) throws BookException {
		try {
			Book result = entityManager.find(Book.class, isbn);
			result.setAvailable(storeService.getStock("books", isbn) > 0);
			return result;
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public Book updateBook(Book bookValue) throws BookException {
		return entityManager.merge(bookValue);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteBookByIsbn(String isbn) throws BookException {
		Query query = entityManager.createNativeQuery("delete from BOOK where isbn = :isbn");
		query.setParameter("isbn", isbn);
		int found = query.executeUpdate();
		if (found != 1) {
			throw new BookException(BookExceptionType.NOT_DELETED, isbn);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Book> findAllBooks() {
		return entityManager.createQuery("select b from Book as b", Book.class).getResultList();
	}


}
