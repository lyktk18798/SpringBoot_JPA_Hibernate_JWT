package com.lyktk.webbangiay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lyktk.webbangiay.domain.User;


/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(@Param("email") String email);

    List<User> findAllByEmailLikeAndAndFullnameLikeAndPhonenumberLikeAndRoleIdIn(String email,
                                                                                              String fullname,
                                                                                              String phonenumber,
                                                                                              List<Integer> roleId);

}
