package org.javacream.books;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BooksApplicationConfig {
//	@Value("${stock}")
//	private int stock;
//
//	@Bean
//	@Scope("singleton")
//	public StoreService storeService() {
//		JdbcStoreService simpleStoreService = new JdbcStoreService();
//		simpleStoreService.setStock(stock);
//		return simpleStoreService;
//	}
	static {
		System.setProperty("http.proxyHost", "dsvbm");
		System.setProperty("http.proxyPort", "8080");

	}
}
