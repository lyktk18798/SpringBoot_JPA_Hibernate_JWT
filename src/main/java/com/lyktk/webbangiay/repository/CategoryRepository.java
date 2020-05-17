package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Category;
import com.lyktk.webbangiay.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * UserRepository
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    List<Category> findAllByNameLikeAndStatusIs(String name, Integer status);

    List<Category> findAllByStatusIs(Integer status);
}
