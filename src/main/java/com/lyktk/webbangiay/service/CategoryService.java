package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Category;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface CategoryService {

	List<Category> findAll(String name);

	void save(Category u);

	void delete(Integer id);
}
