package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * UserRepository
 */
public interface ProducerRepository extends JpaRepository<Producer, Integer>{

    List<Producer> findAllByEmailLikeAndNameLikeAndPhoneLikeAndCategoryIdInAndAndStatusIs(String email,
                                                                                                  String name,
                                                                                                  String phonenumber,
                                                                                                  List<Integer> categoryId,
                                                                                                 Integer status);
    List<Producer> findAllByStatusIs(Integer status);
}
