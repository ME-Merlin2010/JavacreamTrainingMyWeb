package org.javacream.books;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BooksApplicationConfig {
	@Value("${stock}")
	private int stock;
	@Value("${countryCode}")
	private String countryCode;
	@Value("{prefix}")
	private String prefix;
	
	@Bean @Scope("singleton")
	public StoreService storeService() {
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setStock(stock);
		return simpleStoreService;
	}
	
	@Bean @Scope("singleton")
	public IsbnGenerator isbnGenerator() {
		RandomIsbnGenerator randomIsbnGenerator = new RandomIsbnGenerator();
		randomIsbnGenerator.setCountryCode(countryCode);
		randomIsbnGenerator.setPrefix(prefix);
		return randomIsbnGenerator;
	}
	
	@Bean @Scope("singleton")
	public BooksService booksService() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(isbnGenerator());
		mapBooksService.setStoreService(storeService());
		return mapBooksService;
	}
}
