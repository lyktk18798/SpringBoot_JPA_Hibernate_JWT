package com.lyktk.webbangiay.base;

import java.io.Serializable;
import java.util.List;

public interface BasicService<DOMAIN extends Serializable>{

    List<DOMAIN> getAll();
    DOMAIN getById(Long id);
    DOMAIN saveOrUpdate(DOMAIN domain);
}