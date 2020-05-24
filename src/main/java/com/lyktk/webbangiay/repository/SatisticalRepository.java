package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.repository.custom.SatisticalCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface SatisticalRepository extends JpaRepository<OrdersDetails, Integer>, SatisticalCustomRepository {
}
