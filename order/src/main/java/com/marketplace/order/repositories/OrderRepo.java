package com.marketplace.order.repositories;

import com.marketplace.order.models.OrderEntity;
import com.marketplace.order.models.OrderID;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderRepo extends Neo4jRepository<OrderEntity, OrderID> {
}
