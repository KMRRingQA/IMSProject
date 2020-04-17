package com.qa.ims.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

public interface DaoOrderLine<T> {

	BigDecimal calculate(Long id);

	T changeItems(T t);

	List<T> readOrder(Long id);
}