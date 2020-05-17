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

public class SatiscalByBrand implements Serializable {
    /**
     * Author Lyktk
     */
    private static final long serialVersionUID = 1L;

    private Double totalMoney;
    private Long totalQuantity;
    private String name;



}
