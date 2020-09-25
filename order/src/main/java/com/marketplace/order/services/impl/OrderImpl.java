package com.marketplace.order.services.impl;

import com.marketplace.order.models.OrderEntity;
import com.marketplace.order.repositories.OrderRepo;
import com.marketplace.order.services.OrderService;
import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Override
    public void save(OrderEntity orderEntity) throws InvalidEntityToPersistException {
        orderRepo.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) throws NotFoundEntityException {
        if(!orderRepo.findById(orderEntity.getOrderId()).isPresent())
            throw new NotFoundEntityException("Id Invalide","","");
        return orderRepo.save(orderEntity);
    }

    @Override
    public OrderEntity getById(long id) throws NotFoundEntityException {
        return orderRepo.findById(id).get();
    }

    @Override
    public Iterable<OrderEntity> all() throws NotFoundEntityException {
        return orderRepo.findAll();
    }

    @Override
    public void delete(long id) throws NotFoundEntityException {
        orderRepo.deleteById(id);
    }

    @Override
    public List<OrderEntity> allBySellerId(long userId) {
        return orderRepo.findAllBySellerId(userId);
    }

    @Override
    public List<OrderEntity> allByBuyerId(long id) {
        return orderRepo.findAllByBuyerId(id);
    }
}
