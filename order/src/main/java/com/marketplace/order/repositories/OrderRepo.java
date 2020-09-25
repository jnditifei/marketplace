package com.marketplace.order.repositories;

import com.marketplace.order.models.OrderEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends Neo4jRepository<OrderEntity, Long> {
    public List<OrderEntity> findAllByBuyerId(long userId);

    public List<OrderEntity> findAllBySellerId(long userId);

}
