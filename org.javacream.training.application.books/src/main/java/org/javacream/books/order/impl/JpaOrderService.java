package org.javacream.books.order.impl;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderStatus;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaOrderService implements OrderService{

	@Autowired //@Qualifier("traced")
	private StoreService storeService;
	@Autowired
	private BooksService booksService;
	private Random random = new Random(this.hashCode() + System.currentTimeMillis());

	@Transactional
	public Order findOrderById(long id) {
		return entityManager.find(Order.class, id);
	}

	@Transactional
	public List<Order> findOrdersByIsbn(String isbn) {
		String jpaQuery = "select o from OrderEntity as o where o.isbn = :isbn";
		TypedQuery<Order> query = entityManager.createQuery(jpaQuery, Order.class);
		query.setParameter("isbn", isbn);
		//return query.getSingleResult();//RuntimeException, falls order nicht gefunden
		return query.getResultList();
	}

	@Transactional public void update(Order order) {
		entityManager.merge(order);
	}

	//@Transactional //Using Transactional on private Methods is nonsense 
	private void deleteBad(Order order) {
		Order attachedOrder = entityManager.find(Order.class, order.getOrderId());
		entityManager.remove(attachedOrder);
	}
	//@Transactional //Using Transactional on private Methods is nonsense 
	private void deleteOk(Order order) {
		Order attachedOrder = entityManager.getReference(Order.class, order.getOrderId());
		entityManager.remove(attachedOrder);
	}
	//@Transactional //Using Transactional on private Methods is nonsense 
	private void deleteGood(Order order) {
		entityManager.createNativeQuery("delete from ORDER_TABLE where orderid = " + order.getOrderId()).executeUpdate();
	}

	@PersistenceContext private EntityManager entityManager;
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
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
		Order newOrder = new Order(orderId, isbn, totalPrice, status);
		/*
		 * CRUD des EntityManagers
		 * persist
		 * find, createQuery
		 * merge
		 * remove
		 */
		entityManager.persist(newOrder);
		return newOrder;
	}

	@Override
	public Order findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Order order) {
		this.deleteOk(order);
		
	}

}
