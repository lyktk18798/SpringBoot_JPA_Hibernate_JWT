package com.lyktk.webbangiay.repository;

import com.lyktk.webbangiay.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * UserRepository
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("SELECT p FROM Product p WHERE " +
            " (:name is null or p.name like :name) " +
            " and (:code is null or p.code like :code)" +
            " and (:priceFrom is null or p.price >= :priceFrom)" +
            " and (:priceTo is null or p.price <= :priceTo)" +
            " and (:dateFrom is null or p.dateImport >= :dateFrom)" +
            " and (:dateTo is null or p.dateImport <= :dateTo)" +
            " and (p.color.id in :color)" +
            " and (:size is null or p.size = :size)" +
            " and (p.category.id in :categoryId)" +
            " and (p.producer.id in :producerId)" +
            " and (p.productGroup.id in :groupId)" +
            " and p.status = :status"
    )
    List<Product> findProducts(@Param("name") String name,
                              @Param("code") String code,
                              @Param("priceFrom") Integer priceFrom,
                              @Param("priceTo") Integer priceTo,
                              @Param("dateFrom") Date dateFrom,
                              @Param("dateTo") Date dateTo,
                              @Param("color") List<Integer> color,
                              @Param("size") Integer size,
                              @Param("categoryId") List<Integer> categoryId,
                              @Param("producerId") List<Integer> prodcerId,
                              @Param("groupId") List<Integer> groupId,
                                @Param("status") Integer status
    );

    @Query("SELECT p FROM Product p WHERE " +
            " p.name like :name " +
            " and p.productGroup.id in :groupId" +
            " and p.color.id in :colorId" +
            " and p.category.id in :categoryId" +
            " and p.status = :status"
    )
    List<Product> search(@Param("name") String name,
                        @Param("groupId") List<Integer> groupId,
                        @Param("colorId") List<Integer> colorId,
                        @Param("categoryId") List<Integer> categoryId,
                         @Param("categoryId") Integer status
    );


    @Query("SELECT p FROM Product p WHERE " +
            " p.productGroup.id = :groupId"
    )
    List<Product> getProductsByGroupId(@Param("groupId") Integer groupId);


    Product findFirstByOrderByIdDesc();

}
