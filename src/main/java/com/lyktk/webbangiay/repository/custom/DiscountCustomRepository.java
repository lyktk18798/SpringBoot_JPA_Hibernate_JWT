package com.lyktk.webbangiay.repository.custom;

import com.lyktk.webbangiay.domain.custom.Discount;

/**
 * UserRepository
 */
public interface DiscountCustomRepository {

    void discountByAllProducts(Discount discount);
    void discountByGroup(Discount discount);
    void discountByBranch(Discount discount);
    void discountProduct(Discount discount);
}
