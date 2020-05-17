package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.repository.custom.SatisticalCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * UserRepository
 */
public interface SatisticalRepository extends JpaRepository<OrdersDetails, Integer>, SatisticalCustomRepository {

    List<SatiscalByGroup> satiscalByGroup();
}
