package com.platzi.pizzeriaDominos.service;

import com.platzi.pizzeriaDominos.persistence.entity.OrderEntity;
import com.platzi.pizzeriaDominos.persistence.projection.OrderSummary;
import com.platzi.pizzeriaDominos.persistence.repository.OrderRepository;
import com.platzi.pizzeriaDominos.service.DTO.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll (){
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders (){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders (){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders (Integer idCustomer){
        return  this.orderRepository.findCustomerOrder(idCustomer);
    }

    public OrderSummary getSummary (Integer idOrder){
        return this.orderRepository.getSummary(idOrder);
    }


    @Transactional
    public boolean saveRandomOrder (RandomOrderDto dto){
        return this.orderRepository.saveRandomOrder(dto.getIdCustomer(),dto.getMethod());
    }
}