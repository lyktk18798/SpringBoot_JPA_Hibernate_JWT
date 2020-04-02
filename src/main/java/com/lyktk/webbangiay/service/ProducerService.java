package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Producer;
import com.lyktk.webbangiay.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Lyktk
 *
 */
public interface ProducerService{

	public List<Producer> findAllProducer(String email, String name, String phonenumber, Integer categoryId);

	void save(Producer u);

	void delete(Integer id);
}
