package com.lyktk.webbangiay.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BasicRepository<DOMAIN extends Serializable> extends JpaRepository<DOMAIN, Long> {
}
