package com.marketplace.users.controllers;

import com.marketplace.users.models.SellerEntity;
import com.marketplace.users.services.SellerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class SellerControllerTest {
    @Mock
    SellerService mockSellerService ;

    @InjectMocks
    SellerController sellerController = new SellerController();

    @BeforeEach
    void setup(){
        initMocks(this);
    }

    @Test
    void createSeller() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        doNothing().when(mockSellerService).save(any(SellerEntity.class));
        sellerController = spy(sellerController);
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        ResponseEntity response = sellerController.createSeller(seller);
        Mockito.verify(mockSellerService, times(1)).save(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createSellerInvalidEmailOrPasswordException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        InvalidEmailOrPasswordException e = new InvalidEmailOrPasswordException("Email ou mot de passe invalide", "","");
        doThrow(e).when(mockSellerService).save(any(SellerEntity.class));
        ResponseEntity response = sellerController.createSeller(
                new SellerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createSellerInvalidEntityException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        InvalidEntityToPersistException e = new InvalidEntityToPersistException("ID invalide", "","");
        doThrow(e).when(mockSellerService).save(any(SellerEntity.class));
        ResponseEntity response = sellerController.createSeller(
                new SellerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createSellerUnknowException() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        doThrow(RuntimeException.class).when(mockSellerService).save(any(SellerEntity.class));
        ResponseEntity response = sellerController.createSeller(seller);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateSeller() throws NotFoundEntityException {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        seller.setUserId(1);
        when(mockSellerService.update(any(SellerEntity.class))).thenReturn(seller);
        ResponseEntity response = sellerController.update(seller);
        assertTrue(response.getBody().equals(seller));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateNotFoundEntity() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("Id invalid", "", "");
        doThrow(e).when(mockSellerService).update(any(SellerEntity.class));
        ResponseEntity response = sellerController.update(new SellerEntity("AAA", "aaa", "a@a.com", "test"));
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void getBydId() throws NotFoundEntityException {
        SellerEntity seller = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        when(mockSellerService.getById(1)).thenReturn(seller);
        ResponseEntity response = sellerController.getBydId(1);
        assertTrue(response.getBody().equals(seller));
    }

    @Test
    void getByIdInvalidEntityException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "", "");
        doThrow(e).when(mockSellerService).getById(1);
        ResponseEntity response = sellerController.getBydId(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getByIdUnknownException() throws NotFoundEntityException {
        when(mockSellerService.getById(anyInt())).thenThrow(RuntimeException.class);
        ResponseEntity response = sellerController.getBydId(anyInt());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAll() throws NotFoundEntityException {
        SellerEntity seller1 = new SellerEntity("AAA", "aaa", "a@a.com", "test");
        SellerEntity seller2 = new SellerEntity("BBB", "bbb", "b@b.com", "test");
        List<SellerEntity> sellers = new ArrayList<>();
        sellers.add(seller1);
        sellers.add(seller2);
        when(mockSellerService.all()).thenReturn(sellers);
        ResponseEntity response = sellerController.getAll();
        assertTrue(response.getBody().equals(sellers));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "", "");
        doThrow(e).when(mockSellerService).all();
        ResponseEntity response = sellerController.getAll();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllUnknownError() throws NotFoundEntityException {
        when(mockSellerService.all()).thenThrow(RuntimeException.class);
        ResponseEntity response = sellerController.getAll();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void delete() throws NotFoundEntityException {
        doNothing().when(mockSellerService).delete(anyInt());
        sellerController = spy(sellerController);
        ResponseEntity response = sellerController.delete(1);
        Mockito.verify(mockSellerService, times(1)).delete(any(Integer.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "", "");
        doThrow(e).when(mockSellerService).delete(anyInt());
        ResponseEntity response = sellerController.delete(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteUnknownException() throws NotFoundEntityException {
        doThrow(RuntimeException.class).when(mockSellerService).delete(anyInt());
        ResponseEntity response = sellerController.delete(1);
        verify(mockSellerService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}