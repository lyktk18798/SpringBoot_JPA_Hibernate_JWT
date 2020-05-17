package com.lyktk.webbangiay.base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicServiceImpl<DOMAIN extends Serializable> implements BasicService<DOMAIN> {

    public abstract BasicRepository<DOMAIN> getRepository();

    @Override
    public List<DOMAIN> getAll() {
        List<DOMAIN> lst = new ArrayList<>();
        getRepository().findAll().iterator().forEachRemaining(lst::add);
        return lst;
    }

    @Override
    public DOMAIN getById(Long id) {
        return getRepository().findById(id).get();
    }

    @Override
    public DOMAIN saveOrUpdate(DOMAIN domain) {
        getRepository().save(domain);
        return domain;
    }

}
