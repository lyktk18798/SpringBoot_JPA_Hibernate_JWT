package com.lyktk.webbangiay.domain;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class MyUserDetails implements UserDetails{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String role;

	public MyUserDetails(User user) {
		this.username= user.getEmail();
		this.password= user.getPassword();
		this.role= user.getRole().getName();
	}

    public MyUserDetails(String username, String role) {
        this.username= username;
        this.role= role;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
		
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
