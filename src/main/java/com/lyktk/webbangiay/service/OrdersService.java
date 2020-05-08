package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Orders;
import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.models.OrderRequest;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface OrdersService {

    List<Orders> findAllOrders(String code,
                                      String dateFrom,
                                      String dateTo,
                                      Integer status);

    void save(Orders u);

    void buy(OrderRequest orderRequest);

    void delete(Integer id);

    void update(Orders orders);
}
