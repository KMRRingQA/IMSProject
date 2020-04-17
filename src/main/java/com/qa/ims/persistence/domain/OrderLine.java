package com.qa.ims.persistence.domain;

public class OrderLine {

	private Long orderId;

	private Long itemId;

	private Long quantity;

	public OrderLine(Long orderId, Long itemId, Long quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public OrderLine(Long orderLineId, Long orderId, Long itemId, Long quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		OrderLine other = (OrderLine) obj;

		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId)) {
			return false;
		}
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity)) {
			return false;
		}
		return true;
	}

	public Long getItemId() {
		return itemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "order id:" + orderId + " item id:" + itemId + " quantity:" + quantity;
	}

}
