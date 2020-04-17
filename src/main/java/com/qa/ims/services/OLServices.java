package com.qa.ims.services;

import java.math.BigDecimal;
import java.util.List;

public interface OLServices<T> {

	T changeItems(T t);

	public List<T> readOrder(Long id);

	BigDecimal calculate(Long id);

}
