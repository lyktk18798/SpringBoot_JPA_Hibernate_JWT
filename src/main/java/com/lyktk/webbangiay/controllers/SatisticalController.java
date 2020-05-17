package com.lyktk.webbangiay.controllers;

import com.lyktk.webbangiay.domain.custom.SatiscalByBrand;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.domain.custom.SatiscalByMonth;
import com.lyktk.webbangiay.service.SatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/satistical")
public class SatisticalController {

	@Autowired
    private SatisticalService satisticalService;

	@GetMapping("/byGroup")
	public ResponseEntity<?> satisticByGroup(){
		List<SatiscalByGroup> rs;
		String message;
		try{
			rs= satisticalService.satiscalByGroup();
		}catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

		return ResponseEntity.ok(rs);

	}

    @GetMapping("/byBrand")
    public ResponseEntity<?> satisticByBrand(){
        List<SatiscalByBrand> rs;
        String message;
        try{
            rs= satisticalService.satiscalByBrand();
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        return ResponseEntity.ok(rs);

    }

    @GetMapping("/byMonth")
    public ResponseEntity<?> satisticByMonth(){
        List<SatiscalByMonth> rs;
        String message;
        try{
            rs= satisticalService.satiscalByMonth();
        }catch(Exception e){
            message = "An error occurred";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        return ResponseEntity.ok(rs);

    }

}
