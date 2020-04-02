package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Producer;
import com.lyktk.webbangiay.domain.Product;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface ProductService {

	public List<Product> findAllProducer(String code,
                                         String name,
                                         Integer colorId,
                                         Integer priceFrom,
                                         Integer priceTo,
                                         String dateFrom,
                                         String dateTo,
                                         Integer size,
                                         Integer categoryId,
                                         Integer producerId,
                                         Integer groupId);

	public List<Product> search(String name, Integer groupId, Integer colorId, Integer categoryId);

	public List<Product> getProductByGroup(Integer groupId);

	public Product getProductById(Integer productId);

	void save(Product u);

	void delete(Integer id);
}
