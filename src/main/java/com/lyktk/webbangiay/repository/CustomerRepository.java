package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * UserRepository
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public List<Customer> findByEmailLikeAndAndPhoneLike(@Param("email") String email,
                                                         @Param("phone") String phone);

}
