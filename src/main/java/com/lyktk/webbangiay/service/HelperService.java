package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.*;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface HelperService {

     List<Category> findAllCategory();
     List<Role> findAllRole();
     List<Producer> findAllProducer();
     List<Color> findAllColors();
     List<ProductGroup> findAllProductGroups();

     void sendMail(String emailTo);

}

