package com.marketplace.users.repositories;

import com.marketplace.users.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <UserEntity, Integer> {
    public UserEntity findByEmail(String email);

    public UserEntity findByEmailAndPassword(String email, String password);
}
