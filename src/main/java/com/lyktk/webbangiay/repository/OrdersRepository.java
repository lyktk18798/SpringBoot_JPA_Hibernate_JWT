package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * UserRepository
 */
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

    @Query("SELECT p FROM Orders p WHERE " +
            " p.code like :code " +
            "and (:dateFrom is null or p.createDate >= :dateFrom)" +
            "and (:dateTo is null or p.createDate <= :dateTo)" +
            "and (p.status in :status)"
    )

	List<Orders> findAllOrders(@Param("code") String code,
                                @Param("status") List<Integer> status,
                                 @Param("dateFrom") Date dateFrom,
                                 @Param("dateTo") Date dateTo
    );

    Orders findFirstByOrderByIdDesc();

}
