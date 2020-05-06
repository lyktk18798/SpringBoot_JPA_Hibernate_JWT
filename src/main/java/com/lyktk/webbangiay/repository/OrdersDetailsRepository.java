package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * UserRepository
 */
public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, Integer>{

    List<OrdersDetails> findAllByOrdersId(Integer ordersId);

    @Query(value = "select o.product" +
            " from OrdersDetails o " +
            " group by o.product.id " +
            " order by sum(o.quantity) "
    )
    List<Product> getSelliingProduct();
}
