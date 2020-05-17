package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.custom.SatiscalByBrand;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.domain.custom.SatiscalByMonth;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface SatisticalService {

	List<SatiscalByGroup> satiscalByGroup();
    List<SatiscalByBrand> satiscalByBrand();
    List<SatiscalByMonth> satiscalByMonth();

}
