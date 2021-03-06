package com.qa.ims.persistence.dao;

import java.util.List;

public interface DaoCRUD<T> {

	T create(T t);

	void delete(long id);

	List<T> readAll();

	T update(T t);

	List<T> searchName(String name);
}