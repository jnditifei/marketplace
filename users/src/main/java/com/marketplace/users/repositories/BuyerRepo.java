package com.marketplace.users.repositories;

import com.marketplace.users.models.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends JpaRepository<BuyerEntity, Long> {
}
