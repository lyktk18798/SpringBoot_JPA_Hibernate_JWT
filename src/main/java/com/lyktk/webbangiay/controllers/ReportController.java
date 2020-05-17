package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.Report;
import com.lyktk.webbangiay.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
    private ReportService reportService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String productName,
                                         @RequestParam(required = false) String customerName
                                         ) throws Exception{
		List<Report> rs;
		try{
			rs= reportService.findAll(productName, customerName);
		}catch(BadCredentialsException e){
			throw new Exception("", e);
		}

		return ResponseEntity.ok(rs);

	}

}
