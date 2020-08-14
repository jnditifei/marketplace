package com.marketplace.product.services;

import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.services.exceptions.NotFoundEntityException;

import java.util.List;

public interface ProductService extends GenericService<ProductEntity, String> {

    List<ProductEntity> getAllByBrand(String brand) throws NotFoundEntityException;

    Iterable<ProductEntity> rangeOfPrice(long pricelt, long pricegt) throws NotFoundEntityException;
}
