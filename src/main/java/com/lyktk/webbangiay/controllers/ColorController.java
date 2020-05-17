package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Color;
import com.lyktk.webbangiay.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {

	@Autowired
    private ColorService colorService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String name){
		List<Color> rs;
		String message;

		try{
			rs= colorService.findAll(name);
		}catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		return ResponseEntity.ok(rs);

	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody(required = false)Color producer){

        colorService.save(producer);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id){

        colorService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
