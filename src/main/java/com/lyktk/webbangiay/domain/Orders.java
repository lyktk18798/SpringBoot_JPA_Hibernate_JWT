package com.lyktk.webbangiay.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders implements Serializable {
    /**
     * Author Lyktk
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;

    @Column
    private Date receiveDate;

    @NotBlank
    private String address;

    @Column
    private String desciption;

    @NotNull
    private Integer status;

    @Column
    private Integer createBy;

    @Column
    private Integer updateBy;

    @NotBlank
    private String code;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column
    private Double discount;



}
