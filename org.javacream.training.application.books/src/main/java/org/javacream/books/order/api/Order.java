package org.javacream.books.order.api;

import java.io.Serializable;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", isbn=" + isbn + ", totalPrice=" + totalPrice + ", orderStatus="
				+ orderStatus + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}
	public Order(long orderId, String isbn, double totalPrice, OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.isbn = isbn;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
	}
	private long orderId;
	private String isbn;
	private double totalPrice;
	private OrderStatus orderStatus;
	public long getOrderId() {
		return orderId;
	}
	public String getIsbn() {
		return isbn;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
}
