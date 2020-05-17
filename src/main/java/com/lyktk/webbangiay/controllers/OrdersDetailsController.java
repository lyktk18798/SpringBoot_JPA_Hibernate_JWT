package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.service.OrdersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrdersDetailsController {

	@Autowired
    OrdersDetailsService ordersDetailsService;

	@GetMapping("/getDetails/{id}")
	public ResponseEntity<?> getAllUsers(@PathVariable("id") Integer id){
		List<OrdersDetails> rs;
		String message;
		try{
			rs= ordersDetailsService.findAllOrdersDetails(id);
		}catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

		}

		return ResponseEntity.ok(rs);

	}
}
