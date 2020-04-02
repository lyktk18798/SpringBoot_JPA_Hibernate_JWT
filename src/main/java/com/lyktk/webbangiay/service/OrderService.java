package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Orders;
import com.lyktk.webbangiay.domain.Producer;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface OrderService {

	public List<Orders> findAllOrders(String email, String name, String phonenumber, Integer categoryId);
}
