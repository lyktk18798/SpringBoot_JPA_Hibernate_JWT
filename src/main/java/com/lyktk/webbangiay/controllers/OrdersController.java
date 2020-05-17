package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Orders;
import com.lyktk.webbangiay.models.OrderRequest;
import com.lyktk.webbangiay.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {

	@Autowired
    OrdersService ordersService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String code,
										 @RequestParam(required = false) Integer status,
										 @RequestParam(required = false) String dateFrom,
										 @RequestParam(required = false) String dateTo
                                         ){
		List<Orders> rs;
		String message;
		try{
			rs= ordersService.findAllOrders(code, dateFrom, dateTo, status);
		}catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody(required = false)Orders orders){
        ordersService.save(orders);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody(required = false)Orders orders){
        ordersService.update(orders);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/v1/add")
    public ResponseEntity<?> buy(@RequestBody(required = false)OrderRequest orderRequest){
        ordersService.buy(orderRequest);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
