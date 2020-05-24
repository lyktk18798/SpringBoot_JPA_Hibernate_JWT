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
            statusOrders= Arrays.asList(Constant.WAIT_APPROVE,
                                        Constant.NOT_DELIVER,
                                        Constant.DELIVERING,
                                        Constant.DONE);
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
        double totalMoney = orderRequest.getLstProducts().stream().mapToDouble(item
                -> item.getDiscount()*item.getQuantity()*item.getPrice()).sum();
        Orders orders = new Orders();
        orders.setCreateDate(new Date());
        orders.setCustomer(customer);
        orders.setCode("HD"+ StringUtils.leftPad(""+(++old), 6, "0"));
        orders.setStatus(Constant.WAIT_APPROVE);
        orders.setAddress(orderRequest.getAddress());
        orders.setTotalMoney(totalMoney);

        //save order
        Orders newOrder = ordersRepository.save(orders);
        ordersRepository.flush();

        //create order-details
        orderRequest.getLstProducts().forEach(item -> {
            OrdersDetails ordersDetails = new OrdersDetails();

            //find product by idrders
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found product in list product"));
            ordersDetails.setOrders(newOrder);
            ordersDetails.setQuantity(item.getQuantity());
            ordersDetails.setProduct(product);
            ordersDetails.setDiscount(item.getDiscount());
            ordersDetails.setTotalMoney(item.getPrice()*item.getQuantity()*item.getDiscount());
            //save order-details
            ordersDetailsRepository.save(ordersDetails);

            //update quantity products table
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        });


    }

    @Override
    public void delete(Integer id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found orders with id "+id));
        orders.setStatus(Constant.UNREAL_ORDERS); //la don hang ao => xoa
        ordersRepository.save(orders);
    }

    @Override
    public void update(Orders orders) {
        if(orders.getStatus() == Constant.NOT_DELIVER){
            //find customer by id
            Customer customer = customerRepository.findById(orders.getCustomer().getId())
                    .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Not found customer"));

            //verify order and plus point to customer
            customer.setPoint(customer.getPoint() +
                    Integer.valueOf(""+Constant.DEDUCT_PRODUCT*(orders.getTotalMoney())));
        }
        ordersRepository.save(orders);
    }
}
