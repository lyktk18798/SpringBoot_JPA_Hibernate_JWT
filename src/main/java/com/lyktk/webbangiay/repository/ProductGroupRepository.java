package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * UserRepository
 */
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer>{

    List<ProductGroup> findAllByNameLikeAndStatusIs(String name, Integer status);

    List<ProductGroup> findAllByStatusIs(Integer status);
}
