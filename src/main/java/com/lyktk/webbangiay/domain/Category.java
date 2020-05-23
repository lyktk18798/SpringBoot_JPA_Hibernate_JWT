package com.lyktk.webbangiay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createDate", "updateDate"},
        allowGetters = true)
@Getter
@Setter
public class Category implements Serializable {
    /**
     * Author Lyktk
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;

    @NotNull
    private Integer createBy;

    @Column
    private Integer updateBy;

    @Column
    private Integer status;

    @Column
    private String image;
}
