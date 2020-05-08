package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;
import com.lyktk.webbangiay.domain.Orders;
import com.lyktk.webbangiay.domain.OrdersDetails;
import com.lyktk.webbangiay.domain.Product;
import com.lyktk.webbangiay.models.OrderRequest;
import com.lyktk.webbangiay.repository.CustomerRepository;
import com.lyktk.webbangiay.repository.OrdersDetailsRepository;
import com.lyktk.webbangiay.repository.OrdersRepository;
import com.lyktk.webbangiay.repository.ProductRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrdersDetailsRepository ordersDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public void buy(OrderRequest orderRequest) {
        //find customer by id
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found customer"));
        Integer old= ordersRepository.findFirstByOrderByIdDesc().getId() ;

        //create new order
        Orders orders = new Orders();
        orders.setCreateDate(new Date());
        orders.setCustomer(customer);
        orders.setCode("HD"+ StringUtils.leftPad(""+(++old), 6, "0"));
        orders.setStatus(0);
        orders.setAddress(orderRequest.getAddress());

        //save order
        Orders newOrder = ordersRepository.save(orders);
        ordersRepository.flush();

        //create order-details
        orderRequest.getLstProducts().forEach(item -> {
            OrdersDetails ordersDetails = new OrdersDetails();
            //find product by id
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found product in list product"));
            ordersDetails.setOrdersId(newOrder.getId());
            ordersDetails.setQuantity(item.getQuantity());
            ordersDetails.setProduct(product);
            ordersDetails.setDiscount(0.2);
            //save order-details
            ordersDetailsRepository.save(ordersDetails);
        });
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
