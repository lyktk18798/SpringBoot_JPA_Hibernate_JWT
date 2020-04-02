package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.*;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface HelperService {

    public List<Category> findAllCategory();
    public List<Role> findAllRole();
    public List<Producer> findAllProducer();
    public List<Color> findAllColors();
    public List<ProductGroup> findAllProductGroups();

    public void sendMail(String emailTo);

}

