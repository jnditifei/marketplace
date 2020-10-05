package com.marketplace.product.controllers;

import com.google.common.collect.Iterables;
import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.models.enumerations.categoryEnum;
import com.marketplace.product.models.enumerations.conditionEnum;
import com.marketplace.product.services.ProductService;
import com.marketplace.product.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.product.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController = new ProductController();

    @BeforeEach
    void setup(){
        initMocks(this);
    }

    @Test
    void createProductTest() throws InvalidEntityToPersistException {
        doNothing().when(productService).save(any(ProductEntity.class));
        productController = spy(productController);
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        ResponseEntity response = productController.create(product);
        verify(productService, times(1)).save(any());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void crateProductInvalidException() throws InvalidEntityToPersistException {
        InvalidEntityToPersistException e = new InvalidEntityToPersistException("", "", "");
        doThrow(e).when(productService).save(any(ProductEntity.class));
        ResponseEntity response = productController.create(new ProductEntity());
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateProductTest() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        product.setId("123");
        ProductEntity update = new ProductEntity("zzddz", conditionEnum.NEUF, 777l,"dddd","ssss", categoryEnum.IMMOBILIER);
        when(productService.update(any(ProductEntity.class))).thenReturn(update);
        ResponseEntity response = productController.update(product);
        assertTrue(response.getBody().equals(update));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateNotFoundEntity() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        NotFoundEntityException e  = new NotFoundEntityException("", "","");
        doThrow(e).when(productService).update(any(ProductEntity.class));
        ResponseEntity response = productController.update(product);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void getByIdTest() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        when(productService.getById(any(String.class))).thenReturn(product);
        ResponseEntity response = productController.getBydId("123");
        assertEquals(product, response.getBody());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void getByIdNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e  = new NotFoundEntityException("", "","");
        doThrow(e).when(productService).getById(any(String.class));
        ResponseEntity response = productController.getBydId("123");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void getAllTest() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        ProductEntity update = new ProductEntity("zzddz", conditionEnum.NEUF, 777l,"dddd","ssss", categoryEnum.IMMOBILIER);
        List<ProductEntity> products = new ArrayList<>();
        products.add(product);
        products.add(update);
        when(productService.all()).thenReturn(products);
        ResponseEntity response = productController.getAll();
        assertTrue(response.getBody().equals(products));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllNotFoundEntityException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("","","");
        doThrow(e).when(productService).all();
        ResponseEntity response = productController.getAll();
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllByBrand() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.OCCASION, 1200l,"Megane","renault", categoryEnum.AUTOMOBILE);
        ProductEntity product2 = new ProductEntity();
        product2.setTitle("dddd");
        product2.setDescription("eeeee");
        product2.setModel("Megane");
        product2.setBrand("renault");
        product2.setCategory(categoryEnum.AUTOMOBILE);
        List<ProductEntity> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        when(productService.getAllByBrand("renault")).thenReturn(products);
        ResponseEntity response = productController.allByBand("renault");
        assertTrue(response.getBody().equals(products));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getALLBYBrandNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "","");
        doThrow(e).when(productService).getAllByBrand("renault");
        ResponseEntity response = productController.allByBand("renault");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void deleteTest() throws NotFoundEntityException {
        doNothing().when(productService).delete(anyString());
        productController = spy(productController);
        ResponseEntity response = productController.delete("123");
        verify(productService, times(1)).delete(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("","","");
        doThrow(e).when(productService).delete(anyString());
        ResponseEntity response = productController.delete("woaw");
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

    @Test
    void rangeOfPriceTest() throws NotFoundEntityException {
        Iterable<ProductEntity> products = new ArrayList<>();
        when(productService.rangeOfPrice(1l,2l)).thenReturn(products);
        ResponseEntity response = productController.byPrice(1L, 2L);
        assertTrue(response.getBody().equals(products));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void rangeOfPriceNotFoundException() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "", "");
        doThrow(e).when(productService).rangeOfPrice(1l,2L);
        ResponseEntity response = productController.byPrice(1L, 2L);
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
