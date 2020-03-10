package com.marketplace.users.services.implementations;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserImplTest {

    //Inject auto inject userRepo
    @InjectMocks
    private UserService userService= new UserImpl();

    @Mock
    UserRepo userRepo;

    @Test
    void login() throws InvalidEmailOrPasswordException {
        UserEntity user = new UserEntity("AAA", "aaa", "a@a.com", "test");
        when(userRepo.findByEmailAndPassword("a@a.com", "test")).thenReturn(user);
        assertEquals(user, userService.login("a@a.com", "test"));
    }

    @Test
    void loginInvalidEmailOrPasswordTest(){
        Assertions.assertThrows(InvalidEmailOrPasswordException.class, ()-> userService.login("a@a.com", "test") );
    }

}