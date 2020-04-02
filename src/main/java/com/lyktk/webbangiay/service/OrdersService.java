package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Orders;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface OrdersService {

    public List<Orders> findAllOrders(String code,
                                      String dateFrom,
                                      String dateTo,
                                      Integer status);

    void save(Orders u);

    void delete(Integer id);

    void update(Orders orders);
}
