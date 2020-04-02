package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Producer;
import com.lyktk.webbangiay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


/**
 * UserRepository
 */
public interface ProducerRepository extends JpaRepository<Producer, Integer>{

    public List<Producer> findAllByEmailLikeAndNameLikeAndPhoneLikeAndCategoryIdIn(String email,
                                                                                          String name,
                                                                                          String phonenumber,
                                                                                          List<Integer> categoryId);

}
