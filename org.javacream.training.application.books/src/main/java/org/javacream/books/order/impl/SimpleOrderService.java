package org.javacream.books.order.impl;

import java.util.Random;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderStatus;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderService implements OrderService{

	@Autowired @Qualifier("traced")
	private StoreService storeService;
	@Autowired
	private BooksService booksService;
	private Random random = new Random(this.hashCode() + System.currentTimeMillis());

	@Override
	public Order order(String isbn, int number) {
		long orderId = random.nextLong();
		OrderStatus status;
		double totalPrice = 0;
		try {
			double price = booksService.findBookByIsbn(isbn).getPrice();
			totalPrice = number * price;
			int stock = storeService.getStock("books", isbn);
			if (stock < number) {
				status = OrderStatus.PENDING;
			} else {
				status = OrderStatus.OK;
			}
		} catch (Exception e) {
			status = OrderStatus.UNAVAILABLE;
		}
		return new Order(orderId, isbn, totalPrice, status);
	}

}
