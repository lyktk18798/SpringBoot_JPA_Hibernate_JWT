package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Report;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface ReportService {

	List<Report> findAll(String productName, String customerName);
}
