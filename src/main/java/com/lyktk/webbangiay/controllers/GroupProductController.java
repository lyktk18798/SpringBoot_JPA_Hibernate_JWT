package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.ProductGroup;
import com.lyktk.webbangiay.service.GroupProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-product")
public class GroupProductController {

	@Autowired
    private GroupProductService groupProductService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String name){
		List<ProductGroup> rs;
		String message;
		try{
			rs= groupProductService.findAllGroup(name);
		}catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)ProductGroup producer){

        groupProductService.save(producer);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id){

        groupProductService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
