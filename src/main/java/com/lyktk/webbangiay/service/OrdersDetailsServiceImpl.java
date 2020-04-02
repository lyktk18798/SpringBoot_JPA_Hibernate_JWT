package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.repository.OrdersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersDetailsServiceImpl implements OrdersDetailsService {

	@Autowired
	private OrdersDetailsRepository ordersDetailsRepository;

    @Override
    public List<OrdersDetails> findAllOrdersDetails(Integer orderId) {
        return ordersDetailsRepository.findAllByOrdersId(orderId);
    }
}
