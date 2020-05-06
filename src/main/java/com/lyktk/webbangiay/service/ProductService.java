package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Product;
import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface ProductService {

	List<Product> findAllProducer(String code,
                                 String name,
                                 Integer colorId,
                                 Integer priceFrom,
                                 Integer priceTo,
                                 String dateFrom,
                                 String dateTo,
                                 Integer size,
                                 Integer categoryId,
                                 Integer producerId,
                                 Integer groupId
    );

	List<Product> search(String name, Integer groupId, Integer colorId, Integer categoryId);

	List<Product> getProductByGroup(Integer groupId);

	List<Product> getSellingProduct();

	Product getProductById(Integer id);

	void save(Product u);

	void delete(Integer id);
}
