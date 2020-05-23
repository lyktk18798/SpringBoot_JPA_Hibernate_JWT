package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Category;
import com.lyktk.webbangiay.domain.ProductGroup;
import com.lyktk.webbangiay.repository.CategoryRepository;
import com.lyktk.webbangiay.repository.ProductGroupRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll(String name) {
        return categoryRepository.findAllByNameLikeAndStatusIs(
                "%"+name.toUpperCase()+"%",
                Constant.ACTIVE);
    }

    @Override
    public void save(Category u) {
        if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());
            u.setStatus(Constant.ACTIVE);
        }
        categoryRepository.save(u);
    }

    @Override
    public void delete(Integer id) {
        Category producer = categoryRepository.findById(id)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found category with id "+id));
        producer.setStatus(Constant.INACTIVE);
        categoryRepository.save(producer);

    }

}
