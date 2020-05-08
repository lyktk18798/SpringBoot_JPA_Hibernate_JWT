package com.lyktk.webbangiay.models;

import com.lyktk.webbangiay.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {

    private Integer customerId;
    private List<Product> lstProducts;
    private String address;


}
