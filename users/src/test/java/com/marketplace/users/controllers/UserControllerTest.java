package com.marketplace.users.controllers;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    public void setup(){

    }

    @Test
    void loginController() throws InvalidEmailOrPasswordException {
        UserEntity user = new UserEntity("AAA", "aaa", "a@a.com", "test");
        when(userService.login("a@a.com", "test")).thenReturn(user);
        ResponseEntity<Object> response = userController.loginController("a@a.com", "test");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(user == response.getBody());
    }

    @Test
    void loginInvalidEmailOrPassword() throws InvalidEmailOrPasswordException {
        InvalidEmailOrPasswordException exception = new InvalidEmailOrPasswordException("","","");
        when(userService.login("a@a.com", "test")).thenThrow(exception);
        ResponseEntity<Object> response = userController.loginController("a@a.com", "test");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}