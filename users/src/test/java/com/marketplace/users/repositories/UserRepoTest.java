package com.marketplace.users.repositories;

import com.marketplace.users.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/*
*DataJapTest supports rollback after running every test case
 */
@DataJpaTest
class UserRepoTest {

    @Autowired
    UserRepo userRepo;

    @Test
    void findByEmail() {
        UserEntity user = new UserEntity("AAA", "aaa", "a@a.com", "WESH");
        userRepo.save(user);
        UserEntity found = userRepo.findByEmail("a@a.com");
        assertEquals(user.getUserId(), found.getUserId());
    }

    @Test
    void findByEmailAndPassword() {
        UserEntity user = new UserEntity("AAA", "aaa", "a@a.com", "WESH");
        userRepo.save(user);
        UserEntity found = userRepo.findByEmailAndPassword("a@a.com", "WESH");
        assertEquals(user.getUserId(), found.getUserId());
    }
}