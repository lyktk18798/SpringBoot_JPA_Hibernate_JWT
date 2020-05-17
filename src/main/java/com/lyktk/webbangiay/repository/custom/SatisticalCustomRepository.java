package com.lyktk.webbangiay.repository.custom;

import com.lyktk.webbangiay.domain.custom.SatiscalByBrand;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.domain.custom.SatiscalByMonth;

import java.util.List;


/**
 * UserRepository
 */
public interface SatisticalCustomRepository{

    List<SatiscalByGroup> satiscalByGroup();
    List<SatiscalByBrand> satiscalByBrand();
    List<SatiscalByMonth> satiscalByMonth();
}
