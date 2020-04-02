package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.OrdersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * UserRepository
 */
public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, Integer>{

    List<OrdersDetails> findAllByOrdersId(Integer ordersId);
}
