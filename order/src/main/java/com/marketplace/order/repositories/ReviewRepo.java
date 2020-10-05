package com.marketplace.order.repositories;

import com.marketplace.order.models.ReviewEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReviewRepo extends Neo4jRepository<ReviewEntity, Long> {
}
