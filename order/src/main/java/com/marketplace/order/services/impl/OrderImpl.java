package com.marketplace.order.services.impl;

import com.marketplace.order.models.OrderEntity;
import com.marketplace.order.models.OrderID;
import com.marketplace.order.services.OrderService;
import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;

public class OrderImpl implements OrderService {
    @Override
    public void save(OrderEntity orderEntity) throws InvalidEntityToPersistException {

    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) throws NotFoundEntityException {
        return null;
    }

    @Override
    public OrderEntity getById(OrderID id) throws NotFoundEntityException {
        return null;
    }

    @Override
    public Iterable<OrderEntity> all() throws NotFoundEntityException {
        return null;
    }

    @Override
    public void delete(OrderID id) throws NotFoundEntityException {

    }
}
