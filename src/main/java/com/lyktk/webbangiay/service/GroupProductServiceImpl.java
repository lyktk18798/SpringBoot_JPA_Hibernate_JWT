package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.ProductGroup;
import com.lyktk.webbangiay.repository.ProductGroupRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupProductServiceImpl implements GroupProductService {

	@Autowired
	private ProductGroupRepository productGroupRepository;


    @Override
    public List<ProductGroup> findAllGroup(String name) {
        return productGroupRepository.findAllByNameLikeAndStatusIs(
                "%"+name.toUpperCase()+"%",
                Constant.ACTIVE);
    }

    @Override
    public void save(ProductGroup u) {
        if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());
            u.setStatus(Constant.ACTIVE);

        }else{
            u.setUpdateBy(1);
            u.setUpdateDate(new Date());
        }
        productGroupRepository.save(u);
    }

    @Override
    public void delete(Integer id) {
        ProductGroup producer = productGroupRepository.findById(id)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found group with id "+id));
        producer.setStatus(Constant.INACTIVE);
        productGroupRepository.save(producer);

    }

}
