package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Category;
import com.lyktk.webbangiay.domain.ProductGroup;
import com.lyktk.webbangiay.service.CategoryService;
import com.lyktk.webbangiay.service.GroupProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
    private CategoryService categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String name) throws Exception{
		List<Category> rs;
		try{
			rs= categoryService.findAll(name);
		}catch(BadCredentialsException e){
			throw new Exception("", e);
		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)Category producer) throws Exception{
        try{
            categoryService.save(producer);
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);
        }

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id) throws Exception{
        try{
            categoryService.delete(id);
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);
        }

        return ResponseEntity.ok(HttpStatus.OK);

    }

}
