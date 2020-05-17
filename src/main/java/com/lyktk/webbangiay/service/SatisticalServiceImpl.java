package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.custom.SatiscalByBrand;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.domain.custom.SatiscalByMonth;
import com.lyktk.webbangiay.repository.SatisticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatisticalServiceImpl implements SatisticalService {

	@Autowired
	private SatisticalRepository satisticalRepository;


    @Override
    public List<SatiscalByGroup> satiscalByGroup() {
        return satisticalRepository.satiscalByGroup();
    }

    @Override
    public List<SatiscalByBrand> satiscalByBrand() {
        return satisticalRepository.satiscalByBrand();
    }

    @Override
    public List<SatiscalByMonth> satiscalByMonth() {
        return satisticalRepository.satiscalByMonth();
    }

}
