package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.*;
import com.lyktk.webbangiay.repository.*;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Override
    public List<Product> findAllProducer(String code, String name, Integer color, Integer priceFrom, Integer priceTo, String dateFrom, String dateTo, Integer size, Integer categoryId, Integer producerId, Integer groupId) {
        List<Integer> category = Arrays.asList(categoryId);
        List<Integer> producer = Arrays.asList(producerId);
        List<Integer> colors = Arrays.asList(color);
        List<Integer> groups = Arrays.asList(groupId);
        if(categoryId == Constant.ALL_ITEMS){
            List<Category> categories = categoryRepository.findAll();
            category= categories.stream().map(Category::getId).collect(Collectors.toList());
        }
        if(producerId == Constant.ALL_ITEMS){
            List<Producer> producerList = producerRepository.findAll();
            producer= producerList.stream().map(Producer::getId).collect(Collectors.toList());
        }
        if(color == Constant.ALL_ITEMS){
            List<Color> çolorList = colorRepository.findAll();
            colors= çolorList.stream().map(Color::getId).collect(Collectors.toList());
        }
        if(groupId == Constant.ALL_ITEMS){
            List<ProductGroup> groupList = productGroupRepository.findAll();
            groups= groupList.stream().map(ProductGroup::getId).collect(Collectors.toList());
        }
        return productRepository.findProducts("%"+name.toUpperCase()+"%",
                                        "%"+code.toUpperCase()+"%",
                                                priceFrom,
                                                priceTo,
                                                DateTimeUtils.convertStingToDate(dateFrom),
                                                DateTimeUtils.convertStingToDate(dateTo),
                                                colors,
                                                size,
                                                category,
                                                producer,
                                                groups
                                                        );
    }

    @Override
    public List<Product> search(String name, Integer groupId, Integer colorId, Integer categoryId) {
        List<Integer> category = Arrays.asList(categoryId);
        List<Integer> colors = Arrays.asList(colorId);
        List<Integer> groups = Arrays.asList(groupId);
        if(categoryId ==Constant.ALL_ITEMS){
            List<Category> categories = categoryRepository.findAll();
            category= categories.stream().map(Category::getId).collect(Collectors.toList());
        }
        if(colorId == Constant.ALL_ITEMS){
            List<Color> çolorList = colorRepository.findAll();
            colors= çolorList.stream().map(Color::getId).collect(Collectors.toList());
        }
        if(groupId == Constant.ALL_ITEMS){
            List<ProductGroup> groupList = productGroupRepository.findAll();
            groups= groupList.stream().map(ProductGroup::getId).collect(Collectors.toList());
        }
        return productRepository.search("%"+name.toUpperCase()+"%", groups, colors, category);
    }

    @Override
    public List<Product> getProductByGroup(Integer groupId) {
        return productRepository.getProductsByGroupId(groupId);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found product"));
    }

    @Override
    public void save(Product u) {
        if(u.getId() == null){
            Integer old= productRepository.findFirstByOrderByIdDesc().getId() ;
            u.setImportBy(1);
            u.setDateImport(new Date());
            u.setCode("SP"+ u.getProductGroup().getId()+u.getCategory().getId() + StringUtils.leftPad(""+(++old), 4, "0"));

        }else{
            u.setUpdateBy(1);
            u.setUpdateDate(new Date());
        }
        productRepository.save(u);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
