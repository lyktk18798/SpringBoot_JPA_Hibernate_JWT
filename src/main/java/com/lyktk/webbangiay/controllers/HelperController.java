package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.*;
import com.lyktk.webbangiay.domain.custom.Discount;
import com.lyktk.webbangiay.service.DiscountService;
import com.lyktk.webbangiay.service.HelperService;
import com.lyktk.webbangiay.service.ProductService;
import com.lyktk.webbangiay.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/helper")
public class HelperController {

	@Autowired
    private HelperService helperService;

	@Autowired
    private DiscountService discountService;


	@GetMapping("/category/getAll")
	public ResponseEntity<?> getAllCategory(){
		List<Category> rs;
		String message;
		try{
			rs= helperService.findAllCategory();
		}catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

		}

		return ResponseEntity.ok(rs);

	}
    @GetMapping("/role/getAll")
    public ResponseEntity<?> getAllRoles(){
        List<Role> rs;
        String message;
        try{
            rs= helperService.findAllRole();
        }catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

        }

        return ResponseEntity.ok(rs);

    }
    @GetMapping("/producer/getAll")
    public ResponseEntity<?> getAllProducer(){
        List<Producer> rs;
        String message;
        try{
            rs= helperService.findAllProducer();
        }catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

        }

        return ResponseEntity.ok(rs);

    }
    @PostMapping("/savefile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("isTypeUpload") int isTypeUpload) {
        String message;
        String pathUpload = Constant.ROOT_UPLOAD_FILE + "/branch";
        if(isTypeUpload == 1){
            pathUpload = Constant.ROOT_UPLOAD_FILE + "/products";
        }else if(isTypeUpload == 3){
            pathUpload = Constant.ROOT_UPLOAD_FILE + "/group";
        }
        List<String> files = new ArrayList<String>();
        final Path rootLocation = Paths.get(pathUpload);

        try {
            try {
                Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());

            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/color/getAll")
    public ResponseEntity<?> getAllColors(){
        List<Color> rs;
        String message;
        try{
            rs= helperService.findAllColors();
        }catch(BadCredentialsException e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("/groupProduct/getAll")
    public ResponseEntity<?> getAllProductGroup(){
        List<ProductGroup> rs;
        String message;
        try{
            rs= helperService.findAllProductGroups();
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

        }

        return ResponseEntity.ok(rs);

    }

    @PostMapping("/discount")
    public ResponseEntity<?> deleteUser(@RequestBody(required = false)Discount discount){
        discountService.discount(discount);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
