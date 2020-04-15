package com.qa.ims.persistence.domain;

import java.math.BigDecimal;

public class Order {

	private Long order_id;

	private Long cust_id;

	private String date;

	private BigDecimal totalPrice;

	public Order(Long order_id, Long cust_id, String date, BigDecimal totalPrice) {
		this.order_id = order_id;
		this.cust_id = cust_id;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public Order(Long cust_id, String date, BigDecimal totalPrice) {
		this.cust_id = cust_id;
		this.date = date;
		this.totalPrice = totalPrice;
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

		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;

		if (cust_id == null) {
			if (other.cust_id != null)
				return false;
		} else if (!cust_id.equals(other.cust_id))
			return false;

		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;

		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;

		return true;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public String getDate() {
		return date;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((cust_id == null) ? 0 : cust_id.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "order id:" + order_id + " cust id:" + cust_id + " date:" + date + " total Price:" + totalPrice;
	}

}
