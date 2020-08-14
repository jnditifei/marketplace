package com.marketplace.users.controllers;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.services.BuyerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BuyerControllerTest {

    @Mock
    BuyerService mockBuyerService;

    @InjectMocks
    BuyerController buyerController = new BuyerController();

    @BeforeEach
    void setup(){
        initMocks(this);
    }

    @Test
    void createBuyer() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        doNothing().when(mockBuyerService).save(any(BuyerEntity.class));
        buyerController = spy(buyerController);
        BuyerEntity buyer = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        ResponseEntity response = buyerController.createBuyer(buyer);
        verify(mockBuyerService, times(1)).save(any(BuyerEntity.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createInvalidEmailOrpasswordException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        InvalidEmailOrPasswordException e = new InvalidEmailOrPasswordException("Email ou mot de passe invalide", "","");
        doThrow(e).when(mockBuyerService).save(any(BuyerEntity.class));
        ResponseEntity response = buyerController.createBuyer(
                new BuyerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testCreateInvalidEntityException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        InvalidEntityToPersistException e = new InvalidEntityToPersistException("Id Invalid", "", "");
        doThrow(e).when(mockBuyerService).save(any(BuyerEntity.class));
        ResponseEntity response = buyerController.createBuyer(new BuyerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void tesCreateUnknownException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        doThrow(RuntimeException.class).when(mockBuyerService).save(any(BuyerEntity.class));
        ResponseEntity response = buyerController.createBuyer(any(BuyerEntity.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateBuyer() throws NotFoundEntityException {
        BuyerEntity buyer = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        buyer.setUserId(1);
        when(mockBuyerService.update(any(BuyerEntity.class))).thenReturn(buyer);
        ResponseEntity response = buyerController.update(buyer);
        assertTrue(response.getBody().equals(buyer));
        ;       assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateNotFoundEntity() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("Id invalide", "", "");
        doThrow(e).when(mockBuyerService).update(any(BuyerEntity.class));
        ResponseEntity response = buyerController.update(
                new BuyerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void getBuyerById() throws NotFoundEntityException {
        BuyerEntity buyer = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        when(mockBuyerService.getById(1)).thenReturn(buyer);
        ResponseEntity response = buyerController.getBuyerById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(buyer, response.getBody());
    }

    @Test
    void getByIdNotFoundexception() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("Id Invalid", "", "");
        doThrow(e).when(mockBuyerService).getById(any(Integer.class));
        ResponseEntity response = buyerController.getBuyerById(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void getByIdUnknownException() throws NotFoundEntityException {
        doThrow(RuntimeException.class).when(mockBuyerService).getById(anyInt());
        ResponseEntity response = buyerController.getBuyerById(anyInt());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllBuyers() throws NotFoundEntityException {
        BuyerEntity buyer1 = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        BuyerEntity buyer2 = new BuyerEntity("BBB", "bbb", "b@b.com", "test");
        List<BuyerEntity> buyers = new ArrayList<>();
        buyers.add(buyer1);
        buyers.add(buyer2);
        when(mockBuyerService.all()).thenReturn(buyers);
        ResponseEntity response = buyerController.getAllBuyers();
        assertEquals(buyers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("","", "");
        doThrow(e).when(mockBuyerService).all();
        assertEquals(HttpStatus.BAD_REQUEST, buyerController.getAllBuyers().getStatusCode());
        verify(mockBuyerService, times(1)).all();
    }

    @Test
    void getAllUnknownException() throws NotFoundEntityException {
        doThrow(RuntimeException.class).when(mockBuyerService).all();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, buyerController.getAllBuyers().getStatusCode());
        verify(mockBuyerService, times(1)).all();
    }

    @Test
    void deleteBuyer() throws NotFoundEntityException {
        doNothing().when(mockBuyerService).delete(anyInt());
        assertEquals(HttpStatus.OK, buyerController.deleteBuyer(1).getStatusCode());
        verify(mockBuyerService, times(1)).delete(anyInt());
    }

    @Test
    void deleteNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("ID invalid", "", "");
        doThrow(e).when(mockBuyerService).delete(anyInt());
        assertEquals(HttpStatus.BAD_REQUEST, buyerController.deleteBuyer(1).getStatusCode());
        verify(mockBuyerService, times(1)).delete(anyInt());
    }

    @Test
    void deleteUnknownException() throws NotFoundEntityException {
        doThrow(RuntimeException.class).when(mockBuyerService).delete(anyInt());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, buyerController.deleteBuyer(2).getStatusCode());
        verify(mockBuyerService, times(1)).delete(anyInt());
    }
}