package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.custom.Discount;
import com.lyktk.webbangiay.repository.DiscountRepository;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository discountRepository;


    @Override
    public void discount(Discount discount) {
        if(discount.getCategoryId() != null && discount.getCategoryId() != 0){
            discountRepository.discountByBranch(discount);
        }else if(discount.getGroupId() != null && discount.getGroupId() != 0){
            discountRepository.discountByGroup(discount);
        }else if(!StringHelper.isEmpty(discount.getCode())){
            discountRepository.discountProduct(discount);
        }else{
            discountRepository.discountByAllProducts(discount);
        }
    }
}
