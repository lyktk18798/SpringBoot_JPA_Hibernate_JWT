package com.lyktk.webbangiay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lyktk.webbangiay.domain.User;

/**
 * 
 * @author Lyktk
 *
 */
public interface UserService extends UserDetailsService {

	Optional<User> getUserByUsername(String username);

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

	List<User> findAllUsers(String email, String fullname, String phonenumber, Integer role);

	void save(User u);

	void delete(Integer id);

	void forgotPass(String email);
}
