package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Orders;
import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.repository.OrdersRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findAllOrders(String code, String dateFrom, String dateTo, Integer status) {
        List<Integer> statusOrders = Arrays.asList(status);
        if(status == Constant.ALL_ITEMS){
            statusOrders= Arrays.asList(1, 2, 3);
        }
        return ordersRepository.findAllOrders("%"+code.toUpperCase()+"%",
                                                statusOrders,
                                                DateTimeUtils.convertStingToDate(dateFrom),
                                                DateTimeUtils.convertStingToDate(dateTo));
    }

    @Override
    public void save(Orders u) {
        if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());

        }else{
            u.setStatus(2);
            u.setUpdateBy(1);
            u.setUpdateDate(new Date());
        }
        ordersRepository.save(u);
    }

    @Override
    public void addToCart(List<Product> lstProducts) {
        //add new order
        Orders newOrder = new Orders();

        ordersRepository.save(newOrder);
    }

    @Override
    public void delete(Integer id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public void update(Orders orders) {
        if(orders.getStatus() == 1 ){
            orders.setStatus(2);
        }else{
            orders.setStatus(3);
        }
        ordersRepository.save(orders);
    }
}
