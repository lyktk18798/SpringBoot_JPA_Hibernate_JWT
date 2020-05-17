package com.lyktk.webbangiay.repository.custom;

import com.lyktk.webbangiay.domain.custom.SatiscalByBrand;
import com.lyktk.webbangiay.domain.custom.SatiscalByGroup;
import com.lyktk.webbangiay.domain.custom.SatiscalByMonth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SatisticalCustomRepositoryImpl implements SatisticalCustomRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<SatiscalByGroup> satiscalByGroup() {
		StringBuilder sql = new StringBuilder(" select new com.lyktk.webbangiay.domain.custom.SatiscalByGroup(" +
                "sum(d.totalMoney)," +
                " gr.name )");
        createStrQueryByGroup(sql);
		Query query = manager.createQuery(sql.toString(), SatiscalByGroup.class);
		return query.getResultList();
	}

    @Override
    public List<SatiscalByBrand> satiscalByBrand() {
        StringBuilder sql = new StringBuilder(" select new com.lyktk.webbangiay.domain.custom.SatiscalByBrand(" +
                " sum(d.totalMoney), " +
                " sum(d.quantity), " +
                " ca.name )");
        createStrQueryByBrand(sql);
        Query query = manager.createQuery(sql.toString(), SatiscalByBrand.class);
        return query.getResultList();
    }

    @Override
    public List<SatiscalByMonth> satiscalByMonth() {
        StringBuilder sql = new StringBuilder(" select new com.lyktk.webbangiay.domain.custom.SatiscalByMonth(" +
                "sum(d.totalMoney)," +
                " month(o.createDate) )");
        createStrQueryByMonth(sql);
        Query query = manager.createQuery(sql.toString(), SatiscalByMonth.class);
        return query.getResultList();
    }

    private void createStrQueryByGroup(StringBuilder sql){

		sql.append(" from OrdersDetails d ");
		sql.append(" join d.product p");
		sql.append(" join p.productGroup gr");
		sql.append(" group by gr.id");
	}

    private void createStrQueryByBrand(StringBuilder sql){

        sql.append(" from OrdersDetails d ");
        sql.append(" join d.product p");
        sql.append(" join p.category ca");
        sql.append(" group by ca.id");
    }

    private void createStrQueryByMonth(StringBuilder sql){

        sql.append(" from OrdersDetails d ");
        sql.append(" join d.orders o ");
        sql.append(" where year(o.createDate) = year(now()) ");
        sql.append(" group by month(o.createDate) ");
        sql.append(" order by month(o.createDate) asc ");
    }
}