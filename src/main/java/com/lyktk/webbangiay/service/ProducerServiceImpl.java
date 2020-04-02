package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Category;
import com.lyktk.webbangiay.domain.Producer;
import com.lyktk.webbangiay.repository.CategoryRepository;
import com.lyktk.webbangiay.repository.ProducerRepository;
import com.lyktk.webbangiay.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private ProducerRepository producerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Producer> findAllProducer(String email, String name, String phonenumber, Integer categoryId) {
        List<Integer> category = Arrays.asList(categoryId);
        if(categoryId == Constant.ALL_ITEMS){
            List<Category> lst= categoryRepository.findAll();
            category= lst.stream().map(Category::getId).collect(Collectors.toList());
        }
        return producerRepository.findAllByEmailLikeAndNameLikeAndPhoneLikeAndCategoryIdIn("%"+email.toUpperCase()+"%", "%"+name.toUpperCase()+"%", "%"+phonenumber.toUpperCase()+"%", category);
    }

    @Override
    public void save(Producer u) {
        if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());

        }else{
            u.setUpdateBy(1);
            u.setUpdateDate(new Date());
        }
        producerRepository.save(u);
    }

    @Override
    public void delete(Integer id) {
        producerRepository.deleteById(id);
    }

}
