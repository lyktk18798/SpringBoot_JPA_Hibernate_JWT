package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.models.AuthenticationRequest;
import com.lyktk.webbangiay.models.AuthenticationResponse;
import com.lyktk.webbangiay.service.MyUserDetailsService;
import com.lyktk.webbangiay.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
    ProductService productService;


	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String code,
										 @RequestParam(required = false) String name,
										 @RequestParam(required = false) Integer colorId,
										 @RequestParam(required = false) Integer priceFrom,
										 @RequestParam(required = false) Integer priceTo,
										 @RequestParam(required = false) String dateFrom,
										 @RequestParam(required = false) String dateTo,
										 @RequestParam(required = false) Integer size,
										 @RequestParam(required = false) Integer categoryId,
										 @RequestParam(required = false) Integer producerId,
										 @RequestParam(required = false) Integer groupId
    ){
		List<Product> rs;
		String message;
		try{
			rs= productService.findAllProducer(code, name, colorId, priceFrom, priceTo, dateFrom, dateTo, size, categoryId, producerId, groupId);
		}catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)Product product){
        productService.save(product);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id){
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("v1/search")
    public ResponseEntity<?> searchProductByName(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Integer groupId,
                                                 @RequestParam(required = false) Integer color,
                                                 @RequestParam(required = false) Integer category
    ){
        List<Product> rs;
        String message;
        try{
            rs= productService.search(name, groupId, color, category);
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("v1/getProductByGroup/{groupId}")
    public ResponseEntity<?> searchProductByGroupId(@PathVariable("groupId") Integer groupId){
        List<Product> rs;
        String message;
        try{
            rs= productService.getProductByGroup(groupId);
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("v1/getSellingProduct")
    public ResponseEntity<?> getSellingProduct(){
        List<Product> rs;
        String message;
        try{
            rs= productService.getSellingProduct();
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("v1/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
	    return ResponseEntity.ok(productService.getProductById(id));

    }



}
