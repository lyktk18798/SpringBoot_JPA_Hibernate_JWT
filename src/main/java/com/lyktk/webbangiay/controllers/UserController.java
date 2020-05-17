package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.MyUserDetails;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.models.AuthenticationRequest;
import com.lyktk.webbangiay.models.AuthenticationResponse;
import com.lyktk.webbangiay.service.MyUserDetailsService;
import com.lyktk.webbangiay.service.UserService;
import com.lyktk.webbangiay.utils.JwtUtils;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	UserService myUserService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/authenticated")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody(required = false) AuthenticationRequest request){
		String message;
	    try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		}catch(Exception e){
            message = "Incorrect username or password";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		final MyUserDetails userDetails= myUserDetailsService.loadUserByUsername(request.getUsername());
		final String jwt= jwtUtils.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String email,
										 @RequestParam(required = false) String fullName,
										 @RequestParam(required = false) String phoneNumber,
										 @RequestParam(required = false) Integer role) {
		List<User> rs= myUserService.findAllUsers(email, fullName, phoneNumber, role);
		return new ResponseEntity<>(rs, HttpStatus.OK);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)User user) {

        myUserService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id) {

        myUserService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/forgot-pass/{email}")
    public ResponseEntity<?> forgotPass(@PathVariable("email") String email) {
        myUserService.forgotPass(email);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
