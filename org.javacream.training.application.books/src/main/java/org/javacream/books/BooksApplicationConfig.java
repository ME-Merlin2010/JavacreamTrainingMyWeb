package org.javacream.books;

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

	@Bean
	@Scope("singleton")
	public StoreService storeService() {
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setStock(stock);
		return simpleStoreService;
	}

}
