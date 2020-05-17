package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Report;
import com.lyktk.webbangiay.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;


    @Override
    public List<Report> findAll(String productName, String customerName) {
        return reportRepository.search(
                "%"+productName.toUpperCase()+"%",
                "%"+customerName.toUpperCase()+"%"
        );
    }

}
