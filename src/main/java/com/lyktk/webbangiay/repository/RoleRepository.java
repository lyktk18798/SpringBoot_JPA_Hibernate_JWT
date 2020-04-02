package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Category;
import com.lyktk.webbangiay.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * UserRepository
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
}
