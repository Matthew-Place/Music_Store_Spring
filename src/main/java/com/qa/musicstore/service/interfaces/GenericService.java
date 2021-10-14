package com.qa.musicstore.service.interfaces;

import java.util.List;

public interface GenericService<T, K extends Object> {

	public T mapToDTO(K k);

	public List<T> mapToDTO(List<K> k);

	public T create(K k);

	public T update(Integer id, K k);

	public boolean delete(List<Integer> ids);

	public List<T> findById(List<Integer> ids);

	public List<T> findAll();
}
