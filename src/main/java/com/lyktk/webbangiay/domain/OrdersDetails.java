package com.lyktk.webbangiay.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrdersDetails implements Serializable {
    /**
     * Author Lyktk
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double discount;

    @NotNull
    private Integer ordersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private Product product;

    @Transient
    private Integer totalQty;


}
