package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.repository.custom.DiscountCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface DiscountRepository extends JpaRepository<Product, Integer>, DiscountCustomRepository {
}
