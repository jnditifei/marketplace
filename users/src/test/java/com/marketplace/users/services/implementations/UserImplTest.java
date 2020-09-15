package com.marketplace.users.services.implementations;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.models.UserEntity;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class UserImplTest {

    //Inject auto inject userRepo
    @InjectMocks
    private UserService userService= new UserImpl();

    @Mock
    UserRepo userRepo;

    @BeforeEach
    void setup(){
        initMocks(this);
    }

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

    @Test
    void save() {
    }

    @Test
    void saveInvalidEntityToPersist(){
        BuyerEntity buyer = new BuyerEntity();
        buyer.setUserId(1);
        Assertions.assertThrows(InvalidEntityToPersistException.class, ()-> userService.save(buyer));
    }

    @Test
    void saveInvalideEmailorPasswordException() {
    }

    @Test
    void updateNotFoundException() {
        BuyerEntity buyer = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        assertThrows(NotFoundEntityException.class, ()-> userService.update(buyer));
    }

    @Test
    void getById() throws NotFoundEntityException {
        BuyerEntity buyerEntity = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        when(userRepo.findById(1L)).thenReturn(Optional.of(buyerEntity));
        assertEquals(buyerEntity, userService.getById(1L));
    }

    @Test
    void getByIdNotFoundException() {
        Assertions.assertThrows(NotFoundEntityException.class, ()-> userService.getById(1L));
    }

    @Test
    void all() throws NotFoundEntityException {
        UserEntity buyer1 = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        UserEntity buyer2 = new BuyerEntity("BBB", "bbb", "b@b.com", "test");
        List<UserEntity> buyers = new ArrayList<>();
        buyers.add(buyer1);
        buyers.add(buyer2);
        when(userRepo.findAll()).thenReturn(buyers);
        assertEquals(buyer1, userService.all().get(0));
    }

    @Test
    void allNotFoundExceptionTest(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> userService.all());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> userService.delete(1L));
    }

}