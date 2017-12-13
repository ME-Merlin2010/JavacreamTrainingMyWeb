package org.javacream.books.order.api;

import java.util.List;

public interface OrderService {

	Order order(String isbn, int number);
	Order findOrderById(long id);
	List<Order> findOrdersByIsbn(String isbn);
	void update(Order order);
	void delete(Order order);
}
