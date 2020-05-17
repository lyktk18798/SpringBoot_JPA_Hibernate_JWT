package com.lyktk.webbangiay.repository;
import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * UserRepository
 */
public interface ReportRepository extends JpaRepository<Report, Integer>{

    @Query("SELECT p FROM Report p WHERE " +
            " p.product.name like :productName " +
            " and p.customer.email like :customerName "
    )
    List<Report> search(@Param("productName") String productName,
                        @Param("customerName") String customerName
    );
}
