package com.lyktk.webbangiay.repository.custom;

import com.lyktk.webbangiay.domain.custom.Discount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
public class DiscountCustomRepositoryImpl implements DiscountCustomRepository {
	@PersistenceContext
	private EntityManager manager;

    @Override
    public void discountByAllProducts(Discount discount) {
        StringBuilder sql = new StringBuilder(" update Product p set p.discount = :discount");
        Query query = manager.createQuery(sql.toString());
        query.setParameter("discount", discount.getDiscount()/100);
        query.executeUpdate();
    }

    @Override
    public void discountByGroup(Discount discount) {
        StringBuilder sql = new StringBuilder(" update Product p set p.discount = :discount where p.productGroup.id = :groupId");
        Query query = manager.createQuery(sql.toString());
        query.setParameter("discount", discount.getDiscount()/100);
        query.setParameter("groupId", discount.getGroupId());
        query.executeUpdate();
    }

    @Override
    public void discountByBranch(Discount discount) {
        StringBuilder sql = new StringBuilder(" update Product p set p.discount = :discount where p.category.id = :categoryId");
        Query query = manager.createQuery(sql.toString());
        query.setParameter("discount", discount.getDiscount()/100);
        query.setParameter("categoryId", discount.getCategoryId());
        query.executeUpdate();
    }

    @Override
    public void discountProduct(Discount discount) {
        StringBuilder sql = new StringBuilder(" update Product p set p.discount = :discount where p.code = :code");
        Query query = manager.createQuery(sql.toString());
        query.setParameter("discount", discount.getDiscount()/100);
        query.setParameter("code", discount.getCode());
        query.executeUpdate();
    }
}