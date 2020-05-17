package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Color;
import com.lyktk.webbangiay.repository.ColorRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorRepository colorRepository;


    @Override
    public List<Color> findAll(String name) {
        return colorRepository.findAllByNameLikeAndStatusIs(
                "%"+name.toUpperCase()+"%",
                Constant.ACTIVE);
    }

    @Override
    public void save(Color u) {
        Color color = colorRepository.findAllByNameEquals(u.getName())
                .orElseThrow(() -> new LogicException(HttpStatus.FOUND, "Existed color with code "+u.getName()));
        System.out.print(color.getId());
        if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());
            u.setStatus(Constant.ACTIVE);

        }
        colorRepository.save(u);
    }

    @Override
    public void delete(Integer id) {
        Color producer = colorRepository.findById(id)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found color with id "+id));
        producer.setStatus(Constant.INACTIVE);
        colorRepository.save(producer);

    }

}
