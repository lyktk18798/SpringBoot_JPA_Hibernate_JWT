package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.OrdersDetails;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface OrdersDetailsService {
    List<OrdersDetails> findAllOrdersDetails(@Param("orderId") Integer orderId);
}
