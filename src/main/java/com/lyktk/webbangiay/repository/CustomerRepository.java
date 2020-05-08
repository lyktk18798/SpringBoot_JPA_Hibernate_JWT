package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * UserRepository
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	List<Customer> findByEmailLikeAndAndPhoneLike(@Param("email") String email,
                                                         @Param("phone") String phone);


    Optional<Customer> findByEmailAndPassword(@Param("email") String email,
                                              @Param("password") String password);
}
