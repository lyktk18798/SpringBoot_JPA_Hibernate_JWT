package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.*;
import com.lyktk.webbangiay.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/helper")
public class HelperController {

	@Autowired
    private HelperService helperService;

    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("/Users/caokd/Downloads/coreui-free-angular-admin-template-master/src/assets/img/products");

	@GetMapping("/category/getAll")
	public ResponseEntity<?> getAllCategory() throws Exception{
		List<Category> rs= new ArrayList<>();
		try{
			rs= helperService.findAllCategory();
		}catch(BadCredentialsException e){
			throw new Exception("Error", e);

		}

		return ResponseEntity.ok(rs);

	}
    @GetMapping("/role/getAll")
    public ResponseEntity<?> getAllRoles() throws Exception{
        List<Role> rs= new ArrayList<>();
        try{
            rs= helperService.findAllRole();
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);

        }

        return ResponseEntity.ok(rs);

    }
    @GetMapping("/producer/getAll")
    public ResponseEntity<?> getAllProducer() throws Exception{
        List<Producer> rs= new ArrayList<>();
        try{
            rs= helperService.findAllProducer();
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);

        }

        return ResponseEntity.ok(rs);

    }
    @PostMapping("/savefile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            try {
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
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
    public ResponseEntity<?> getAllColors() throws Exception{
        List<Color> rs= new ArrayList<>();
        try{
            rs= helperService.findAllColors();
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);

        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("/groupProduct/getAll")
    public ResponseEntity<?> getAllProductGroup() throws Exception{
        List<ProductGroup> rs= new ArrayList<>();
        try{
            rs= helperService.findAllProductGroups();
        }catch(BadCredentialsException e){
            throw new Exception("Error", e);

        }

        return ResponseEntity.ok(rs);

    }
}
