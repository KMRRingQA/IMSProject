package com.qa.ims.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

public interface DaoLine<T> {

	BigDecimal calculate(Long id);

	T changeItems(T t);

	List<T> readOrder2(Long id);

	String readOrder(Long orderId);
}