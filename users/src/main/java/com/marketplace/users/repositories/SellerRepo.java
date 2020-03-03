package com.marketplace.users.repositories;

import com.marketplace.users.models.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository <SellerEntity, Integer> {
}
