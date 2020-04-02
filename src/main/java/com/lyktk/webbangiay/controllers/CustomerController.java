package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Customer;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.models.AuthenticationRequest;
import com.lyktk.webbangiay.models.AuthenticationResponse;
import com.lyktk.webbangiay.service.CustomerService;
import com.lyktk.webbangiay.service.MyUserDetailsService;
import com.lyktk.webbangiay.service.UserService;
import com.lyktk.webbangiay.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
    CustomerService customerService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String email,
										 @RequestParam(required = false) String phonenumber,
										 @RequestParam(required = false) Integer page,
										 @RequestParam(required = false) Integer size
                                         ) throws Exception{
		List<Customer> rs= new ArrayList<>();
		try{
			rs= customerService.findAllCustomers(email, phonenumber);
		}catch(BadCredentialsException e){
			throw new Exception("Error", e);

		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)Customer customer) throws Exception{
        try{
            customerService.save(customer);
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);
        }

        return ResponseEntity.ok(HttpStatus.OK);

    }
}
