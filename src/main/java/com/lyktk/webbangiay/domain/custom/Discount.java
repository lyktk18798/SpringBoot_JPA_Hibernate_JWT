package com.lyktk.webbangiay.domain.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Discount implements Serializable {
    /**
     * Author Lyktk
     */
    private static final long serialVersionUID = 1L;

    private Double discount;
    private Integer categoryId;
    private Integer groupId;
    private String code;



}
