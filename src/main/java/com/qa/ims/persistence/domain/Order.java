package com.qa.ims.persistence.domain;

import java.math.BigDecimal;

public class Order {

	private Long orderId;

	private Long custId;

	private String date;

	private BigDecimal totalPrice;

	public Order(Long orderId, Long custId, String date, BigDecimal totalPrice) {
		this.orderId = orderId;
		this.custId = custId;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public Order(Long custId, String date, BigDecimal totalPrice) {
		this.custId = custId;
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

		if (orderId == null) {
			if (other.orderId != null) {
				return false;
			}
		} else if (!orderId.equals(other.orderId)) {
			return false;
		}
		if (custId == null) {
			if (other.custId != null) {
				return false;
			}
		} else if (!custId.equals(other.custId)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (totalPrice == null) {
			if (other.totalPrice != null) {
				return false;
			}
		} else if (!totalPrice.equals(other.totalPrice)) {
			return false;
		}
		return true;
	}

	public Long getCustId() {
		return custId;
	}

	public String getDate() {
		return date;
	}

	public Long getOrderId() {
		return orderId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "order id:" + orderId + " cust id:" + custId + " date:" + date + " total Price:" + totalPrice;
	}

}
