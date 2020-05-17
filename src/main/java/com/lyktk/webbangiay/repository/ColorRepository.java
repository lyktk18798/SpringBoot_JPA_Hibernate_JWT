package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * UserRepository
 */
public interface ColorRepository extends JpaRepository<Color, Integer>{

    List<Color> findAllByNameLikeAndStatusIs(String name, Integer status);

    List<Color> findAllByStatusIs(Integer status);

    boolean existsAllByNameEquals(String name);
}
