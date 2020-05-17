package com.lyktk.webbangiay.base;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public abstract class BasicController<DOMAIN extends Serializable> {



    public abstract BasicService getService();

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<DOMAIN> getAll(){
        return getService().getAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public DOMAIN getById(@PathVariable Long id){
        return (DOMAIN) getService().getById(id);
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public DOMAIN saveOrUpdate(@RequestBody DOMAIN domain){

        return (DOMAIN) getService().saveOrUpdate(domain);
    }

}
