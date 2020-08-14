package com.marketplace.users.services.implementations;


import com.marketplace.users.models.SellerEntity;
import com.marketplace.users.repositories.SellerRepo;
import com.marketplace.users.services.SellerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerImplTest {

    @Mock
    SellerRepo sellerRepo;

    // auto inject sellerRepo
    @InjectMocks
    private SellerService sellerService = new SellerImpl();

    @Test
    void save() {}

    @Test
    void saveInvalidEntityToPersistTest(){
        SellerEntity seller = new SellerEntity();
        seller.setUserId(1);
        Assertions.assertThrows(InvalidEntityToPersistException.class, ()-> sellerService.save(seller));
    }

    @Test
    void saveInvalidEmailOrPassword() {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
    }


    @Test
    void updateNotFoundException() {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        assertThrows(NotFoundEntityException.class, ()-> sellerService.update(seller));
    }

    @Test
    void getById() throws NotFoundEntityException {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        when(sellerRepo.findById(1)).thenReturn(Optional.of(seller));
        assertEquals(seller, sellerService.getById(1));
    }

    @Test
    void getByIdNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> sellerService.getById(1));
    }

    @Test
    void all() throws NotFoundEntityException {
        SellerEntity seller1 = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        SellerEntity seller2 = new SellerEntity("BBB", "bbb", "b@b.com", "test");
        List<SellerEntity> sellers = new ArrayList<>();
        sellers.add(seller1);
        sellers.add(seller2);
        when(sellerRepo.findAll()).thenReturn(sellers);
        assertEquals(seller1, sellerService.all().get(0));
    }

    @Test
    void allNotFoundExceptionTest(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> sellerService.all());
    }

    @Test
    void delete() {

    }

    @Test
    void deleteNotFoundExceptionTest(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> sellerService.delete(1));
    }
}