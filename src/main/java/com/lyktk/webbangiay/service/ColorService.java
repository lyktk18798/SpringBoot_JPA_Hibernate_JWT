package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Color;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface ColorService {

	List<Color> findAll(String name);

	void save(Color u);

	void delete(Integer id);
}
