package com.marketplace.product.services;

import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.models.enumerations.categoryEnum;
import com.marketplace.product.models.enumerations.conditionEnum;
import com.marketplace.product.repositories.ProductRepo;
import com.marketplace.product.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.product.services.exceptions.NotFoundEntityException;
import com.marketplace.product.services.impementations.ProductImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductImplTest {

    @InjectMocks
    ProductImpl productService = new ProductImpl();

    @Mock
    ProductRepo repo;

    @Test
    void saveInvalidException(){
        ProductEntity product = new ProductEntity();
        product.setId("123");
        Assertions.assertThrows(InvalidEntityToPersistException.class, ()-> productService.save(product));
    }

    @Test
    void updateNotFoundException(){
        ProductEntity product = new ProductEntity();
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.update(product));
    }

    @Test
    void getByIdTest() throws NotFoundEntityException {
        ProductEntity product = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        when(repo.findById("123")).thenReturn(Optional.of(product));
        assertEquals(product, productService.getById("123"));
    }

    @Test
    void getByIdNotFoundException() {
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.getById("123"));
    }

    @Test
    void all() throws NotFoundEntityException {
        ProductEntity product1 = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        ProductEntity product2 = new ProductEntity("dddd", conditionEnum.NEUF, 200l,"ssss","dddd", categoryEnum.LOISIRS);
        List<ProductEntity> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(repo.findAll()).thenReturn(products);
        assertTrue(productService.all().equals(products));
    }

    @Test
    void getAllNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.all());
    }

    @Test
    void deleteByIdNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.delete("123"));
    }

    @Test
    void getAllByBrandNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.getAllByBrand("dddd"));
    }

    @Test
    void getByRangeOfPriceNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> productService.rangeOfPrice(2L, 3l));
    }

}
