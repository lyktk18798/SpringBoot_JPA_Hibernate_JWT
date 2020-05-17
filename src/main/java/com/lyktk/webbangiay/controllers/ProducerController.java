package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Producer;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.models.AuthenticationRequest;
import com.lyktk.webbangiay.models.AuthenticationResponse;
import com.lyktk.webbangiay.service.MyUserDetailsService;
import com.lyktk.webbangiay.service.ProducerService;
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
@RequestMapping("/api/producer")
public class ProducerController {

	@Autowired
    private ProducerService producerService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String email,
										 @RequestParam(required = false) String fullname,
										 @RequestParam(required = false) String phonenumber,
										 @RequestParam(required = false) Integer categoryId){
		List<Producer> rs;
		String message;
		try{
			rs= producerService.findAllProducer(email, fullname, phonenumber, categoryId);
		}catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)Producer producer){

        producerService.save(producer);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id){

        producerService.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

}
