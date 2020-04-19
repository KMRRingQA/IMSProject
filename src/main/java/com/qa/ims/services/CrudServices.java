package com.qa.ims.services;

import java.util.List;

public interface CrudServices<T> {

	T create(T t);

	void delete(Long id);

	public List<T> readAll();

	T update(T t);

	List<T> searchName(String name);

}
