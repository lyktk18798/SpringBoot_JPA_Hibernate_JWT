package com.lyktk.webbangiay.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lyktk.webbangiay.domain.MyUserDetails;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user= repo.findByEmail(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found user with "+username));
		
		return user.map(MyUserDetails::new).get();
	}
}
