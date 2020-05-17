package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.ProductGroup;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface GroupProductService {

	List<ProductGroup> findAllGroup(String name);

	void save(ProductGroup u);

	void delete(Integer id);
}
