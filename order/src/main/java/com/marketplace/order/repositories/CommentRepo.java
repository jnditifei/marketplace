package com.marketplace.order.repositories;

import com.marketplace.order.models.CommentEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepo extends Neo4jRepository<CommentEntity, Long> {
}
