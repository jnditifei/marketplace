package com.marketplace.order.services;

import com.marketplace.order.models.OrderEntity;

import java.util.List;

public interface OrderService extends GenericService<OrderEntity, Long>{
    List<OrderEntity> allBySellerId(long userId);

    List<OrderEntity> allByBuyerId(long id);
}
